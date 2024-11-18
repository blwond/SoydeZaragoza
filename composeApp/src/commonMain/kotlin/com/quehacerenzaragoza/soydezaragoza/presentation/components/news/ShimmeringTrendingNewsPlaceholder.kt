package com.quehacerenzaragoza.soydezaragoza.presentation.components.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmeringTrendingNewsPlaceholder() {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(top = 8.dp)
                .shimmer(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.TopStart)
                        .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(16.dp)
                            .shimmer()
                            .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp)
                .shimmer()
                .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                .padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(80.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(8.dp).weight(1f))

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(14.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(14.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )
        }
    }
}
