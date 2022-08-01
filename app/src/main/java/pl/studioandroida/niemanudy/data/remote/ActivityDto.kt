package pl.studioandroida.niemanudy.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.studioandroida.niemanudy.domain.model.Activity

@JsonClass(generateAdapter = true)
data class ActivityDto(
    @Json(name = "activity") val activity: String,
    @Json(name = "type") val type: String,
    @Json(name = "participants") val participants: Int,
    @Json(name = "link") val link: String
    )

fun ActivityDto.toActivity(): Activity{
    return Activity(
        activity = activity,
        type = type,
        participants = participants,
        link = link

    )
}


