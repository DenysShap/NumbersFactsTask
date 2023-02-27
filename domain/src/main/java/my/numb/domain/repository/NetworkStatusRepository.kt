package my.numb.domain.repository

import kotlinx.coroutines.flow.Flow

interface NetworkStatusRepository {
    fun getNetworkStatus(): Flow<Boolean>
}