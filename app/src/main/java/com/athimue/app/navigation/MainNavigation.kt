package com.athimue.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.athimue.app.composable.favorites.FavoritesComposable
import com.athimue.app.composable.home.HomeComposable
import com.athimue.app.composable.library.LibraryComposable
import com.athimue.app.composable.playlist.PlaylistComposable
import com.athimue.app.composable.search.SearchComposable
import com.athimue.app.composable.track.TrackComposable

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Library : Screen("library")
    object Favorites : Screen("favorites")
    object Playlist : Screen("playlist/{playlistId}")
    object Track : Screen("track/{trackId}")
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            MenuBottomBar(
                currentRoute = navController.currentDestination?.route,
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onSearchClick = { navController.navigate(Screen.Search.route) },
                onLibraryClick = { navController.navigate(Screen.Library.route) },
                onFavoriteClick = { navController.navigate(Screen.Favorites.route) }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeComposable()
            }
            composable(Screen.Search.route) {
                SearchComposable()
            }
            composable(Screen.Library.route) {
                LibraryComposable(
                    onPlaylistClick = {
                        navController.navigate(
                            Screen.Playlist.route.replace(
                                oldValue = "{playlistId}",
                                newValue = it.toString()
                            )
                        )
                    },
                    onEditPlaylist = {})
            }
            composable(Screen.Favorites.route) {
                FavoritesComposable()
            }
            composable(
                route = Screen.Playlist.route,
                arguments = listOf(navArgument("playlistId") {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) { playlistId ->
                PlaylistComposable(
                    playlistId = playlistId.arguments?.getInt("playlistId") ?: -1,
                    onBack = { navController.navigate(Screen.Library.route) }
                )
            }
            composable(Screen.Track.route) {
                TrackComposable()
            }
        }
    }
}


@Composable
fun MenuBottomBar(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        NavigationBarItem(selected = currentRoute == Screen.Home.route,
            onClick = onHomeClick,
            icon = { Icon(Icons.Rounded.Home, contentDescription = "Home") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedTextColor = MaterialTheme.colorScheme.secondary
            ),
            label = { Text("Home") })
        NavigationBarItem(selected = currentRoute == Screen.Search.route,
            onClick = onSearchClick,
            icon = { Icon(Icons.Rounded.Search, contentDescription = "Search") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedTextColor = MaterialTheme.colorScheme.secondary
            ),
            label = { Text("Search") })
        NavigationBarItem(selected = currentRoute == Screen.Library.route,
            onClick = onLibraryClick,
            icon = { Icon(Icons.Rounded.List, contentDescription = "Library") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedTextColor = MaterialTheme.colorScheme.secondary
            ),
            label = { Text("Library") })
        NavigationBarItem(selected = currentRoute == Screen.Favorites.route,
            onClick = onFavoriteClick,
            icon = { Icon(Icons.Rounded.Favorite, contentDescription = "Favorites") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.secondary,
                unselectedTextColor = MaterialTheme.colorScheme.secondary
            ),
            label = { Text("Favorites") })
    }
}
