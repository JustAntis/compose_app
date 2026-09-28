package com.example.firstcomposeapp.samples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R

@Preview(
    name = "Light - Pixel 7",
    device = Devices.PIXEL_9,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NewsClient(){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp),
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ){
            Image(modifier = Modifier
                .padding(16.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background),
                painter = painterResource(R.drawable.ic_group_logo),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "/fired", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "14:27",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                )
            }

            Icon(
                painterResource(R.drawable.three_dots_svgrepo_com),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(text = "It's probably I am...", modifier = Modifier.padding(start = 12.dp, end = 12.dp))
        Image(
            painter = painterResource(R.drawable.images),
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .border(1.dp, MaterialTheme.colorScheme.background )
                .padding(12.dp),
        ) {
            Text(text = "873", fontWeight = FontWeight.Light, fontSize = 24.sp)
            Image(
                painter = painterResource(R.drawable.ic_views_count),
                contentDescription = "",
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier
                .weight(1f))

            Text(text = "206", fontWeight = FontWeight.Light, fontSize = 24.sp)
            Image(painter = painterResource(R.drawable.ic_like), contentDescription = "")

            Text(text = "23", fontWeight = FontWeight.Light, fontSize = 24.sp)
            Image(painter = painterResource(R.drawable.ic_comment), contentDescription = "")

            Text(text = "18", fontWeight = FontWeight.Light, fontSize = 24.sp)
            Image(painter = painterResource(R.drawable.ic_share), contentDescription = "")
        }
    }
}