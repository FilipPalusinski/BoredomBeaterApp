package pl.studioandroida.boredombeater.data.mapper

import pl.studioandroida.boredombeater.data.local.ActivityEntity
import pl.studioandroida.boredombeater.data.remote.ActivityDto
import pl.studioandroida.boredombeater.domain.model.Activity

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