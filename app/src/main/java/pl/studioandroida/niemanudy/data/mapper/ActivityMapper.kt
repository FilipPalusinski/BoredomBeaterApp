package pl.studioandroida.niemanudy.data.mapper

import pl.studioandroida.niemanudy.data.local.ActivityEntity
import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.domain.model.Activity

fun ActivityDto.toActivity(): Activity {
    return Activity(
        activity = activity,
        type = type,
        participants = participants,
        link = link
    )
}

fun ActivityEntity.toActivity(): Activity {
    return Activity(
        activity = activity,
        type = type,
        participants = participants,
        link = link,
        id = id
    )
}

fun Activity.toActivityEntity(): ActivityEntity {
    return ActivityEntity(
        activity = activity,
        type = type,
        participants = participants,
        link = link,
        id = id
    )
}