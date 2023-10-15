package com.bcan.arabamcomassignment.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArabamTopAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    startIcon: Int?,
    endIcon: Int?,
    startIconClick: () -> Unit,
    endIconClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
    ) {
        TopAppBar(
            title = {
                title?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                    )
                }
            },
            backgroundColor = Color.White,
            navigationIcon = {
                startIcon?.let {
                    IconButton(onClick = startIconClick) {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = Black
                        )
                    }
                }
            },
            actions = {
                when (endIcon) {
                    null -> Spacer(modifier = Modifier.width(68.dp))
                    else -> IconButton(
                        onClick = endIconClick,
                        modifier = Modifier
                            .width(68.dp)
                            .padding(start = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = endIcon),
                            contentDescription = null,
                            tint = Black
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        ArabamDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ArabamDivider(modifier: Modifier = Modifier, color: Color = LightGray) {
    Divider(
        color = color,
        thickness = 1.dp,
        modifier = modifier
    )
}