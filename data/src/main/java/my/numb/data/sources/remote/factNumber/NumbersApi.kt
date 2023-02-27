package my.numb.data.sources.remote.factNumber

import androidx.annotation.Keep
import my.numb.data.sources.remote.factNumber.model.FactAboutNumberResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@Keep
interface NumbersApi {

    @GET("{number}?json")
    suspend fun getNumberFact(@Path("number") number: Long): Response<FactAboutNumberResponse>

    @GET("random/math?json")
    suspend fun getRandomNumberFact(): Response<FactAboutNumberResponse>
}