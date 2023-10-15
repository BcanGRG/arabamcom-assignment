package com.bcan.arabamcomassignment.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.arabamcomassignment.data.model.response.CarDetailResponse
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

data class CarDetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val detail: CarDetailResponse? = null
)

@HiltViewModel
class CarDetailViewModel @Inject constructor(
    private val arabamRepository: ArabamRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CarDetailUiState> = MutableStateFlow(CarDetailUiState())
    val uiState: StateFlow<CarDetailUiState> = _uiState.asStateFlow()
    fun getCarDetail(id: Int) {
        viewModelScope.launch {
            arabamRepository.getCarDetail(id).collectLatest { result ->
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
                                detail = result.data
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

    fun errorMessageShown() {
        _uiState.update { state ->
            state.copy(errorMessage = null)
        }
    }
}