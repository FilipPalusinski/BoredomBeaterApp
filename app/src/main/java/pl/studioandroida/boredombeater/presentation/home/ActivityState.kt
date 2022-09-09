package pl.studioandroida.boredombeater.presentation.home

import pl.studioandroida.boredombeater.domain.model.Activity

data class ActivityState(
    val isLoading: Boolean = false,
    val activity: Activity? = null,
    val error: String = ""
)