package com.bcan.arabamcomassignment.data.repository

import com.bcan.arabamcomassignment.data.service.ArabamService
import javax.inject.Inject

class ArabamRepositoryImpl @Inject constructor(
    private val arabamService: ArabamService
) : ArabamRepository {
}