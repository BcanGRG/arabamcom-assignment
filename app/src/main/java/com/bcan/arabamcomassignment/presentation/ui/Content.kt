package com.bcan.arabamcomassignment.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ArabamContent(
    topBarModifier: Modifier = Modifier,
    title: String?,
    startIcon: Int? = null,
    endIcon: Int? = null,
    startIconClick: () -> Unit = {},
    endIconClick: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    composable: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ArabamTopAppBar(
                modifier = topBarModifier,
                title = title,
                startIcon = startIcon,
                endIcon = endIcon,
                startIconClick = { startIconClick() },
                endIconClick = { endIconClick() }
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = backgroundColor
        ) {
            composable()
        }
    }
}