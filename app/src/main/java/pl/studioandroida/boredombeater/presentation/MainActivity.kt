package pl.studioandroida.boredombeater.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.studioandroida.boredombeater.presentation.favourite.FavouriteScreen
import pl.studioandroida.boredombeater.presentation.favourite.FavouriteViewModel
import pl.studioandroida.boredombeater.presentation.home.HomeScreen
import pl.studioandroida.boredombeater.presentation.home.HomeViewModel
import pl.studioandroida.boredombeater.ui.theme.BoredomBeaterTheme



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredomBeaterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val HomeViewModel: HomeViewModel = hiltViewModel()
                    val FavViewModel: FavouriteViewModel = hiltViewModel()

                    val navController = rememberNavController()

                    Scaffold(
                        topBar = { } ,
                        content = {padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                Navigation(navController = navController, HomeViewModel, FavViewModel)
                            }
                        },
                        bottomBar = {BottomNavigationBar(navController)}
                    )            }
        }
    }
}

    @Composable
    fun Navigation(
        navController: NavHostController,
        HomeViewModel: HomeViewModel = hiltViewModel(),
        FavViewModel: FavouriteViewModel = hiltViewModel()) {

        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route
        ){
            composable(
                route = NavigationItem.Home.route
            ) {
                HomeScreen(HomeViewModel)
            }
            composable(
                route = NavigationItem.Favourite.route
            ) {
                FavouriteScreen(FavViewModel)
                FavViewModel.getActivities()
            }
        }
    }



    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourite
        )
        BottomNavigation(
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    label = { Text(text = item.title) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

