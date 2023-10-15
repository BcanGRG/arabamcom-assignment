package com.bcan.arabamcomassignment.presentation.list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bcan.arabamcomassignment.presentation.list.components.CarListItem
import com.bcan.arabamcomassignment.presentation.ui.ArabamContent
import com.bcan.arabamcomassignment.presentation.ui.LoadingIndicator
import com.bcan.arabamcomassignment.presentation.util.navigation.Screen

@Composable
fun CarListScreen(
    navController: NavHostController,
    viewModel: CarListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val content = uiState.list

    ArabamContent(
        title = "İlan Listesi"
    ) {
        uiState.errorMessage?.let { message ->
            LaunchedEffect(message) {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.errorMessageShown()
            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }

        content?.let { list ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                items(list,
                    key = { car -> car.id ?: 0 }
                ) { car ->
                    CarListItem(
                        car = car,
                        onClick = {
                            navController.navigate(Screen.DetailScreen.withArgs(car.id.toString()))
                        }
                    )
                }
            }
        }
    }
}