package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import com.example.firstcomposeapp.ui.theme.InstagramProfile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = "32"
            val followers = "34"
            val followings = "10"
            FirstComposeAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    InstagramProfile(posts, followers, followings)
                }
            }
        }
    }
}

@Preview(
    name = "Light - Pixel 7",
    device = Devices.PIXEL_9,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TestText(){
    Text(buildAnnotatedString {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
            append("Hello")
        }
    })
}


