package com.athimue.app.navigation

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.athimue.app.composable.album.AlbumComposable
import com.athimue.app.composable.artist.ArtistComposable
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
    object Playlist : Screen("playlist/{id}")
    object Track : Screen("track/{id}")
    object Album : Screen("album/{id}")
    object Artist : Screen("artist/{id}")
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
                HomeComposable(
                    onPopularTrackClick = { trackId ->
                        navController.navigate(
                            Screen.Track.route.replace(
                                oldValue = "{id}",
                                newValue = trackId.toString()
                            )
                        )
                    },
                    onPopularAlbumClick = { albumId ->
                        navController.navigate(
                            Screen.Album.route.replace(
                                oldValue = "{id}",
                                newValue = albumId.toString()
                            )
                        )
                    },
                    onPopularArtistClick = { artistId ->
                        navController.navigate(
                            Screen.Artist.route.replace(
                                oldValue = "{id}",
                                newValue = artistId.toString()
                            )
                        )
                    },
                )
            }
            composable(Screen.Search.route) {
                SearchComposable(
                    onClick = { filter, id ->
                        Log.d("COUCOU", "$filter, $id")
                        when (filter) {
                            "Track" -> navController.navigate(
                                Screen.Track.route.replace(
                                    oldValue = "{id}",
                                    newValue = id.toString()
                                )
                            )
                            "Album" -> navController.navigate(
                                Screen.Album.route.replace(
                                    oldValue = "{id}",
                                    newValue = id.toString()
                                )
                            )
                            "Artist" -> navController.navigate(
                                Screen.Artist.route.replace(
                                    oldValue = "{id}",
                                    newValue = id.toString()
                                )
                            )
                        }

                    },
                )
            }
            composable(Screen.Library.route) {
                LibraryComposable(
                    onPlaylistClick = { playlistId ->
                        navController.navigate(
                            Screen.Playlist.route.replace(
                                oldValue = "{id}",
                                newValue = playlistId.toString()
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
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        defaultValue = -1
                    })
            ) { playlistId ->
                PlaylistComposable(
                    playlistId = playlistId.arguments?.getInt("id") ?: -1,
                    onBack = { navController.popBackStack() }
                )
            }
            composable(
                route = Screen.Track.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.LongType
                        defaultValue = -1
                    })
            ) { trackId ->
                TrackComposable(
                    trackId = trackId.arguments?.getLong("id") ?: -1,
                    onBack = { navController.popBackStack() })
            }
            composable(
                route = Screen.Album.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.LongType
                        defaultValue = -1
                    })
            ) { albumId ->
                AlbumComposable(
                    albumId = albumId.arguments?.getLong("id") ?: -1,
                    onBack = { navController.popBackStack() })
            }
            composable(
                route = Screen.Artist.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.LongType
                        defaultValue = -1
                    })
            ) { artistId ->
                ArtistComposable(
                    artistId = artistId.arguments?.getLong("id") ?: -1,
                    onBack = { navController.popBackStack() })
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
        NavigationItem(
            isSelected = currentRoute == Screen.Home.route,
            text = "Home",
            imageVector = Icons.Rounded.Home,
            onClick = onHomeClick
        )
        NavigationItem(
            isSelected = currentRoute == Screen.Search.route,
            text = "Search",
            imageVector = Icons.Rounded.Search,
            onClick = onSearchClick
        )
        NavigationItem(
            isSelected = currentRoute == Screen.Library.route,
            text = "Library",
            imageVector = Icons.Rounded.List,
            onClick = onLibraryClick
        )
        NavigationItem(
            isSelected = currentRoute == Screen.Favorites.route,
            text = "Favorites",
            imageVector = Icons.Rounded.Favorite,
            onClick = onFavoriteClick
        )
    }
}

@Composable
private fun RowScope.NavigationItem(
    isSelected: Boolean,
    text: String,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    NavigationBarItem(selected = isSelected,
        onClick = onClick,
        icon = { Icon(imageVector, contentDescription = text) },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.secondary
        ),
        label = { Text(text) })
}
