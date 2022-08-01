package pl.studioandroida.niemanudy.domain.model

data class Activity(
    val activity: String,
    val type: String,
    val participants: Int,
    val link: String
)