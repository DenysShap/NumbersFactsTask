package my.numb.numbersfactstask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import my.numb.domain.repository.NetworkStatusRepository
import my.numb.domain.repository.NumbersFactsRepository
import my.numb.domain.usecases.*
import my.numb.domain.util.DispatcherProvider

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetFactNumberUseCase(
        repository: NumbersFactsRepository,
        dispatcherProvider: DispatcherProvider
    ) = GetFactNumberUseCase(repository, dispatcherProvider)

    @Provides
    fun provideGetRandomFactNumberUseCase(
        repository: NumbersFactsRepository,
        dispatcherProvider: DispatcherProvider
    ) = GetRandomFactNumberUseCase(repository, dispatcherProvider)

    @Provides
    fun provideGetAllFactsNumbersUseCase(
        repository: NumbersFactsRepository
    ) = GetAllFactsNumbersUseCase(repository)

    @Provides
    fun provideDeleteAllFactsNumbersUseCase(
        repository: NumbersFactsRepository,
        dispatcherProvider: DispatcherProvider
    ) = DeleteAllFactsNumbersUseCase(repository, dispatcherProvider)

    @Provides
    fun provideNetworkStatusUseCase(
        repository: NetworkStatusRepository
    ) = NetworkStatusUseCase(repository)
}