package pl.studioandroida.niemanudy.presentation.favourite

import pl.studioandroida.niemanudy.domain.model.Activity


data class FavouriteListState(
    val isLoading: Boolean = false,
    val activities: List<Activity> = emptyList(),
    val error: String = ""
)