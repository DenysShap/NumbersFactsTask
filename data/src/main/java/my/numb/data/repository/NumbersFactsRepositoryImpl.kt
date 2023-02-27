package my.numb.data.repository

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import my.numb.core.util.Constants
import my.numb.data.mapper.toFactAboutNumber
import my.numb.data.mapper.toFactAboutNumberEntity
import my.numb.data.mapper.toFactAboutNumberList
import my.numb.data.sources.local.factNumber.InterestingFactsDao
import my.numb.domain.util.DispatcherProvider
import my.numb.data.sources.remote.factNumber.NumbersApi
import my.numb.domain.model.FactAboutNumber
import my.numb.domain.repository.NumbersFactsRepository
import my.numb.domain.util.Resource

class NumbersFactsRepositoryImpl(
    private val numbersApi: NumbersApi,
    private val dispatchersProvider: DispatcherProvider,
    private val interestingFactsDao: InterestingFactsDao
) : NumbersFactsRepository {

    override suspend fun getFactAboutNumber(number: Long): Resource<FactAboutNumber> =
        withContext(dispatchersProvider.io) {
            try {
                val response = numbersApi.getNumberFact(number)
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    interestingFactsDao.addFact(result.toFactAboutNumberEntity())
                    Resource.Success(result.toFactAboutNumber())
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: Constants.ERROR)
            }
        }

    override suspend fun getRandomFactAboutNumber(): Resource<FactAboutNumber> =
        withContext(dispatchersProvider.io) {
            try {
                val response = numbersApi.getRandomNumberFact()
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    interestingFactsDao.addFact(result.toFactAboutNumberEntity())
                    Resource.Success(result.toFactAboutNumber())
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: Constants.ERROR)
            }
        }

    override fun getAllFactsNumber(): Flow<Resource<List<FactAboutNumber>>> = flow {
        emitAll(interestingFactsDao.getAllFacts().map {
            Resource.Success(it.toFactAboutNumberList())
        })
    }.flowOn(dispatchersProvider.io)

    override suspend fun eraseAllFacts() = withContext(dispatchersProvider.io) {
        interestingFactsDao.deleteAllFacts()
    }
}