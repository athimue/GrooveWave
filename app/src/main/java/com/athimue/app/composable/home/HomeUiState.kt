package com.athimue.app.composable.home

data class HomeUiState(
    var isLoading: Boolean = false,
    var albums: List<LazyRowItemModel>? = null,
    var artists: List<LazyRowItemModel>? = null,
    var tracks: List<LazyRowItemModel>? = null
)