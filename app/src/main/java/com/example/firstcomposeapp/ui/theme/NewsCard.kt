package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun NewsCard() {
    Column {
        PostHeader()
        Image(
            painter = painterResource(R.drawable.io_mem),
            contentDescription = null
        )
        PostInfo()
    }
}

@Composable
private fun PostInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconWithText(Icons.Default.RemoveRedEye, "912")

        Spacer(modifier = Modifier.weight(1f))

        IconWithText(Icons.Default.Repeat, "324")

        IconWithText(Icons.Default.AddComment, "53")

        IconWithText(Icons.Default.FavoriteBorder, "831")
    }
}
@Composable
private fun IconWithText(icon: ImageVector, text: String){
    Icon(
        imageVector = icon,
        contentDescription = null,
    )
    Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.ExtraLight)
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CardTravel,
            contentDescription = "",
            tint = Color.LightGray,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(8.dp),
        )
        Column() {
            Text(text = "/dev/memes")
            Text(
                text = "9:37",
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )
    }
}