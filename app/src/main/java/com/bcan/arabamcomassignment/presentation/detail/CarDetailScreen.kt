package com.bcan.arabamcomassignment.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bcan.arabamcomassignment.R
import com.bcan.arabamcomassignment.presentation.detail.components.CarImagePager
import com.bcan.arabamcomassignment.presentation.ui.ArabamContent
import com.bcan.arabamcomassignment.presentation.ui.LoadingIndicator
import com.bcan.arabamcomassignment.presentation.util.formatImageResolution

@Composable
fun CarDetailScreen(
    navController: NavHostController,
    carId: Int?,
    viewModel: CarDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ArabamContent(
        title = "İlan Detayı",
        startIcon = R.drawable.ic_back,
        startIconClick = {
            navController.popBackStack()
        }
    ) {

        carId?.let {
            LaunchedEffect(key1 = it) {
                viewModel.getCarDetail(it)
            }
        }

        uiState.errorMessage?.let { message ->
            LaunchedEffect(message) {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.errorMessageShown()
            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }

        val photos = uiState.detail?.photos
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            photos?.let {

                if (photos.isEmpty()) {

                }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape
                ) {
                    CarImagePager(
                        modifier = Modifier.wrapContentSize(),
                        itemsCount = photos.size
                    ) { index ->
                        AsyncImage(
                            model = photos[index].formatImageResolution(),
                            contentDescription = "",
                            modifier = Modifier.height(250.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }

    }
}
