package my.numb.domain.usecases

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import my.numb.domain.repository.NumbersFactsRepository

class GetAllFactsNumbersUseCase(
    private val repository: NumbersFactsRepository
) {
    operator fun invoke() = flow {
        emitAll(repository.getAllFactsNumber())
    }
}