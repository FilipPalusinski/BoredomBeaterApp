package pl.studioandroida.boredombeater.presentation.favourite

import pl.studioandroida.boredombeater.domain.model.Activity


data class FavouriteListState(
    val isLoading: Boolean = false,
    val activities: List<Activity> = emptyList(),
    val error: String = ""
)