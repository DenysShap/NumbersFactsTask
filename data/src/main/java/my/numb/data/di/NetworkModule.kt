package my.numb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import my.numb.data.sources.remote.factNumber.NumbersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val MAIN_SERVER = "http://numbersapi.com/"

@Module
@DisableInstallInCheck
object NetworkModule {
    private val retrofit = Retrofit.Builder()
        .baseUrl(MAIN_SERVER)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()

    @Singleton
    @Provides
    fun retrofitClient(): NumbersApi {
        return retrofit.create(NumbersApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideServices(): NumbersApi = NetworkModule.retrofitClient()
}