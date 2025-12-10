package com.example.uipractice.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uipractice.R
import com.example.uipractice.model.Trend
import com.example.uipractice.ui.theme.CategoryOvalColor
import com.example.uipractice.ui.theme.DarkBlue
import com.example.uipractice.ui.theme.SearchBarColor
import com.example.uipractice.ui.theme.TextGray

@Composable
fun AnimatedItem(visible: Boolean, delay: Int, content: @Composable () -> Unit) {
    val density = LocalDensity.current
    val slideDistance = with(density) { 50.dp.roundToPx() }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(700, delay))
                + slideInVertically(animationSpec = tween(700, delay))
        {
                    slideDistance
        }

    ) {
        content()
    }

}
@Composable
@Preview
fun HeaderSection(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBlue)
            .padding(32.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top=16.dp)
        ){
            Image(
                painter = painterResource(R.drawable.tharun),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Hi Tharun",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.notification),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Find your Favorites \nHere!",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 35.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "", onValueChange = {},
            placeholder = {Text(text = "Search ...", color = Color.White.copy(0.7f))},
            leadingIcon = { Icon(painterResource(R.drawable.search), contentDescription = null, tint = Color.White) },
            trailingIcon = {Icon(painterResource(R.drawable.microphone), contentDescription = null, tint = Color.White)},

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = SearchBarColor,
                unfocusedContainerColor = SearchBarColor,
                cursorColor = Color.White
            ),
            singleLine = true

        )

    }
}

@Composable
fun CategoryItem(iconRes:Int,title: String){
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.width(100.dp).height(120.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier.size(60.dp).clip(CircleShape).background(CategoryOvalColor),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }

}
@Preview
@Composable
fun MiddleSection(){
    Box(modifier = Modifier.fillMaxWidth()){
        Image(
            painter = painterResource(R.drawable.arc_2),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            CategoryItem(
                iconRes = R.drawable.favorites,
                title = "Favorites"
                )
            CategoryItem(
                iconRes = R.drawable.message,
                title = "Message"
            )
            CategoryItem(
                iconRes = R.drawable.social,
                title = "Social"
            )
        }
    }
}

@Composable

fun TrendItem(trend: Trend){
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .width(205.dp)
            .height(260.dp)
            .padding(bottom = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(trend.picRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = trend.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray,
                maxLines = 2,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = trend.subtitle,
                fontSize = 12.sp,
                color = DarkBlue,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
    }
}
@Composable
@Preview
fun TrendItemPreview(){
    val trend= Trend(
        title = "Mountain",
        subtitle = "Himalaya",
        picRes = R.drawable.trends
    )
    TrendItem(trend=trend)

}
@Composable
@Preview
fun TrendSection(){
    val trends=listOf(
        Trend("Future in AI, what will tomorrow be like?","The National",R.drawable.trends),
        Trend("Important points in concluding a work contract","Reuters",R.drawable.trends2),
        Trend("Future in AI, what will tomorrow be like?","The National",R.drawable.trends)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top=32.dp, bottom = 12.dp)
        ) {
            Text(
                text = "Trends",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "View All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue,
                modifier = Modifier.clickable{}
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(trends){item->TrendItem(item)}
        }
    }

}