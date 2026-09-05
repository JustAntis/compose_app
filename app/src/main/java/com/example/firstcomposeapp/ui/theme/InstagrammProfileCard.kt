package com.example.firstcomposeapp.ui.theme

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R


@Preview
@Composable
fun InstagramProfilePreviewLight() {
    val posts = "32"
    val followers = "34"
    val followings = "10"
    FirstComposeAppTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        InstagramProfile(posts, followers, followings)
    }
}

@Preview
@Composable
fun InstagramProfilePreviewDark() {
    val posts = "32"
    val followers = "34"
    val followings = "10"
    FirstComposeAppTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        InstagramProfile(posts, followers, followings)
    }
}

@Composable
fun InstagramProfile(
    posts: String = "340",
    followers: String = "210",
    followings: String = "33"
) {
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
            Button(
                onClick = { },
                border = BorderStroke(2.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                )
            ) {
                Text(text = "Follow")
            }
        }
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

