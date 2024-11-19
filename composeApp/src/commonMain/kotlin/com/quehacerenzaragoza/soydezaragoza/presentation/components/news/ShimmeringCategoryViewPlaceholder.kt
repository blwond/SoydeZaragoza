package com.quehacerenzaragoza.soydezaragoza.presentation.components.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmeringCategoryViewPlaceholder() {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 15.dp)
            .shimmer()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(65.dp)
                .height(20.dp)
                .shimmer()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}
