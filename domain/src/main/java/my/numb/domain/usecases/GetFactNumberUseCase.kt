package my.numb.domain.usecases

import kotlinx.coroutines.withContext
import my.numb.domain.repository.NumbersFactsRepository
import my.numb.domain.util.DispatcherProvider

class GetFactNumberUseCase(
    private val repository: NumbersFactsRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke(number: Long) = withContext(dispatcherProvider.io) {
        repository.getFactAboutNumber(number)
    }
}