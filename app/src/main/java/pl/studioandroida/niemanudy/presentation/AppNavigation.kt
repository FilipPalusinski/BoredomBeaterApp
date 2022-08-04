package pl.studioandroida.niemanudy.presentation

private const val ROUTE_HOME = "home"
private const val ROUTE_FAV = "favourite"


sealed class AppNavigation(val route: String){
    object activityHome: AppNavigation(ROUTE_HOME)

    object activity
}