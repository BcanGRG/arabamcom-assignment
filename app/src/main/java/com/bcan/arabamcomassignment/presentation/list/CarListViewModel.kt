package com.bcan.arabamcomassignment.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.arabamcomassignment.data.model.CarListQueries
import com.bcan.arabamcomassignment.data.model.response.CarListResponse
import com.bcan.arabamcomassignment.data.repository.ArabamRepository
import com.bcan.arabamcomassignment.data.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CarListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val list: List<CarListResponse>? = null
)

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val arabamRepository: ArabamRepository
) : ViewModel() {


    private val _uiState: MutableStateFlow<CarListUiState> = MutableStateFlow(CarListUiState())
    val uiState: StateFlow<CarListUiState> = _uiState.asStateFlow()

    fun getCarList(queries: CarListQueries) {
        viewModelScope.launch {
            arabamRepository.getCarList(queries).collectLatest { result ->
                when (result) {
                    is NetworkResult.OnLoading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                        }
                    }

                    is NetworkResult.OnSuccess -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = null,
                                list = result.data
                            )
                        }
                    }

                    is NetworkResult.OnError -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            }
        }
    }

}