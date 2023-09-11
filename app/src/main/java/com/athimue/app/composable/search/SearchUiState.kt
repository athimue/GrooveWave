package com.athimue.app.composable.search

import com.athimue.app.composable.playlist.PlaylistUiModel

data class SearchUiState(
    var isLoading: Boolean = false,
    var playlists: List<PlaylistUiModel> = listOf(),
    var searchResult: List<SearchResultModel> = listOf()
)