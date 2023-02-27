package my.numb.domain.repository

import kotlinx.coroutines.flow.Flow
import my.numb.domain.model.FactAboutNumber
import my.numb.domain.util.Resource

interface NumbersFactsRepository {

    suspend fun getFactAboutNumber(number: Long): Resource<FactAboutNumber>

    suspend fun getRandomFactAboutNumber(): Resource<FactAboutNumber>

    fun getAllFactsNumber(): Flow<Resource<List<FactAboutNumber>>>

    suspend fun eraseAllFacts()
}