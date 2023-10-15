package com.bcan.arabamcomassignment.data.repository

import com.bcan.arabamcomassignment.data.model.CarListQueries
import com.bcan.arabamcomassignment.data.model.response.CarDetailResponse
import com.bcan.arabamcomassignment.data.model.response.CarListResponse
import com.bcan.arabamcomassignment.data.service.ArabamService
import com.bcan.arabamcomassignment.data.util.NetworkResult
import com.bcan.arabamcomassignment.data.util.sendRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArabamRepositoryImpl @Inject constructor(
    private val arabamService: ArabamService
) : ArabamRepository {
    override suspend fun getCarList(query: CarListQueries): Flow<NetworkResult<List<CarListResponse>>> {
        return sendRequest {
            arabamService.getCarList(
                sort = query.sort,
                sortDirection = query.sortDirection,
                skip = query.skip,
                take = query.take ?: 10
            )
        }
    }

    override suspend fun getCarDetail(id: Int): Flow<NetworkResult<CarDetailResponse>> {
        return sendRequest { arabamService.getCarDetail(id) }
    }
}