package my.numb.domain.usecases

import kotlinx.coroutines.withContext
import my.numb.domain.repository.NumbersFactsRepository
import my.numb.domain.util.DispatcherProvider

class GetRandomFactNumberUseCase(
    private val repository: NumbersFactsRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke() = withContext(dispatcherProvider.io) {
        repository.getRandomFactAboutNumber()
    }
}