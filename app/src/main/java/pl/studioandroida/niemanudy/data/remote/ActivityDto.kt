package pl.studioandroida.niemanudy.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivityDto(
    @Json(name = "activity") val activity: String,
    @Json(name = "type") val type: String,
    @Json(name = "participants") val participants: Int,
    @Json(name = "price") val price: Double,
    @Json(name = "key") val key: String,
    @Json(name = "link") val link: String,
    @Json(name = "accessibility") val accessibility: Double,

    )




