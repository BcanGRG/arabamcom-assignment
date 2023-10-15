package com.bcan.arabamcomassignment.presentation.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarImagePager(
    modifier: Modifier = Modifier,
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val pagerState = rememberPagerState()
    val currentPageNumber = pagerState.currentPage + 1

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {

        HorizontalPager(
            pageCount = itemsCount,
            state = pagerState
        ) { page -> itemContent(page) }

        Surface(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.BottomStart),
            shape = CircleShape,
            color = Color.Transparent
        ) {
            Text(text = "$currentPageNumber / $itemsCount")
        }
    }
}