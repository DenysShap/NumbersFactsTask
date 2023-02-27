package my.numb.data.mapper

import my.numb.data.sources.local.factNumber.entity.FactAboutNumberEntity
import my.numb.data.sources.remote.factNumber.model.FactAboutNumberResponse
import my.numb.domain.model.FactAboutNumber

internal fun FactAboutNumberResponse.toFactAboutNumber() =
    FactAboutNumber(
        number = number,
        text = text
    )

internal fun List<FactAboutNumberEntity>.toFactAboutNumberList() =
    map { factAboutNumberIntity ->
        FactAboutNumber(
            number = factAboutNumberIntity.number,
            text = factAboutNumberIntity.fact
        )
    }

internal fun FactAboutNumberResponse.toFactAboutNumberEntity() =
    FactAboutNumberEntity(
        number = number,
        fact = text
    )
