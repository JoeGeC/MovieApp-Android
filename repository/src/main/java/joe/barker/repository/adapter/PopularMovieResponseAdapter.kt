package joe.barker.repository.adapter

import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.response.PopularMediaResponse

fun PopularMediaResponse?.convert(): List<MediaDetails>? =
    this?.results?.map { it.convert() }