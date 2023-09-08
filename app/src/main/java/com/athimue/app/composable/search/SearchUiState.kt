package com.athimue.app.composable.search

data class SearchUiState(
    var isLoading: Boolean = false,
    var searchResult: List<SearchResultModel>
)