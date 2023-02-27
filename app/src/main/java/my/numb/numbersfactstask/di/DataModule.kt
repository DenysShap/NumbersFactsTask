package my.numb.numbersfactstask.di

import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.numb.data.repository.NetworkStatusRepositoryImpl
import my.numb.data.repository.NumbersFactsRepositoryImpl
import my.numb.data.sources.local.factNumber.InterestingFactsDao
import my.numb.data.sources.remote.factNumber.NumbersApi
import my.numb.domain.repository.NetworkStatusRepository
import my.numb.domain.repository.NumbersFactsRepository
import my.numb.domain.util.DispatcherProvider

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideNumberFactRepositoryRemote(
        numbersApi: NumbersApi,
        dispatcherProvider: DispatcherProvider,
        interestingFactsDao: InterestingFactsDao
    ): NumbersFactsRepository =
        NumbersFactsRepositoryImpl(numbersApi, dispatcherProvider, interestingFactsDao)

    @Provides
    fun provideNetworkRepository(
        connectivityManager: ConnectivityManager
    ): NetworkStatusRepository = NetworkStatusRepositoryImpl(connectivityManager)
}