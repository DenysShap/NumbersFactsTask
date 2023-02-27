package my.numb.data.sources.remote.factNumber.model

import com.google.gson.annotations.SerializedName

data class FactAboutNumberResponse(
    @SerializedName("number") val number: Long,
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String,
    @SerializedName("found") val found: Boolean
)