package my.numb.domain.model

import java.io.Serializable

// we can use Parcelable but I don't want to do additional data mapping in presentation layer
data class FactAboutNumber(
    val number: Long,
    val text: String
) : Serializable