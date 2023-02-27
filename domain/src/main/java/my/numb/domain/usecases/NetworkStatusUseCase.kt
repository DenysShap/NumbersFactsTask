package my.numb.domain.usecases

import my.numb.domain.repository.NetworkStatusRepository

class NetworkStatusUseCase(
    private val repository: NetworkStatusRepository
) {
    operator fun invoke() = repository.getNetworkStatus()
}