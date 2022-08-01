package pl.studioandroida.niemanudy.presentation.activity_home

import pl.studioandroida.niemanudy.domain.model.Activity

data class ActivityState(
    val isLoading: Boolean = false,
    val activity: Activity? = null,
    val error: String = ""
)