package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.StatisticItem
import com.example.firstcomposeapp.domain.StatisticType


@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit
) {
    Card(modifier = modifier) {
        Column {
            PostHeader(feedPost = feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText,
                fontSize = 18.sp
            )
            Image(
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null
            )
            PostInfo(
                statistics = feedPost.statistics,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener,
                onShareClickListener = onShareClickListener,
                onViewsClickListener = onViewsClickListener
            )
        }
    }
}

@Composable
private fun PostInfo(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onViewsClickListener: (StatisticItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val views = statistics.getItemByType(StatisticType.VIEWS)
        IconWithText(
            Icons.Default.RemoveRedEye,
            text = views.count.toString(),
            onItemClickListener = {
                onViewsClickListener(views)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        val shares = statistics.getItemByType(StatisticType.SHARES)
        IconWithText(
            Icons.Default.Repeat,
            text = shares.count.toString(),
            onItemClickListener = {
                onShareClickListener(shares)
            }
        )

        val comments = statistics.getItemByType(StatisticType.COMMENTS)
        IconWithText(
            Icons.Default.AddComment,
            text = comments.count.toString(),
            onItemClickListener = {
                onCommentClickListener(comments)
            }
        )

        val likes = statistics.getItemByType(StatisticType.LIKES)
        IconWithText(
            Icons.Default.FavoriteBorder,
            text = likes.count.toString(),
            onItemClickListener = {
                onLikeClickListener(likes)
            }
        )
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem{
    return this.find { it.type == type } ?: throw IllegalStateException()
}
@Composable
private fun IconWithText(icon: ImageVector, text: String, onItemClickListener:() -> Unit){
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
        Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.ExtraLight)
    }

}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(feedPost.avatarResId),
            contentDescription = "",
            tint = Color.LightGray,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
        )
        Column() {
            Text(text = feedPost.communityName)
            Text(
                text = feedPost.publicationDate,
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