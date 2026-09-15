package com.example.firstcomposeapp.ui.theme

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.MainViewModelInstagram
import com.example.firstcomposeapp.R


@Composable
fun InstagramProfile(
    posts: String = "340",
    followers: String = "210",
    followings: String = "33",
    viewModel: MainViewModelInstagram = hiltViewModel()
) {
    val isFollowed = viewModel.isFollowing.collectAsState()
    Log.d("RECOMPOSITION","InstagramProfile")
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(
            topStart = 6.dp,
            topEnd = 6.dp
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(20.dp)

    ) {
        Log.d("RECOMPOSITION","Card")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.instagram),
                contentDescription = "App",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            UserStats(posts, "Posts")
            UserStats(followers, "Followers")
            UserStats(followings, "Followings")
        }
        Column(
            modifier = Modifier
                .padding(10.dp),

        ) {
            Text(text = "Instagram", fontFamily = FontFamily.Serif)
            Text(text = "#YoursToMake")
            Text(text = "www.youtube.com/just_antis")
            FollowButton(
                isFollowed = isFollowed
            ){
                viewModel.changeFollowingStatus()
            }
        }
    }
}
@Composable
private fun FollowButton(isFollowed: State<Boolean>, clickListener:() -> Unit){
    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed.value){
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            }else {
                MaterialTheme.colorScheme.primary
            }
        )
    ) {
        Log.d("RECOMPOSITION","Button")
        val text = if (isFollowed.value){
            "Unfollow"
        } else{
            "Follow"
        }
        Text(text = text)
    }
}
@Composable
fun UserStats(value: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 10.sp
        )
    }
}

