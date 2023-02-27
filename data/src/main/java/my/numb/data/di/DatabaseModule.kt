package my.numb.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.numb.data.sources.local.factNumber.FactsNumbersDatabase
import my.numb.data.sources.local.factNumber.InterestingFactsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    internal fun provideFactsNumbersDatabase(@ApplicationContext context: Context): InterestingFactsDao =
        FactsNumbersDatabase.getDatabase(context).getInterestingFactDao()
}