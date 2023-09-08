package com.athimue.app.composable.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.GetAlbumSearchUseCase
import com.athimue.domain.usecase.GetArtistSearchUseCase
import com.athimue.domain.usecase.GetTrackSearchUseCase
import com.athimue.domain.usecase.definition.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTrackSearchUseCase: GetTrackSearchUseCase,
    private val getAlbumSearchUseCase: GetAlbumSearchUseCase,
    private val getArtistSearchUseCase: GetArtistSearchUseCase,
) : ViewModel() {

    var searchUiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState(false, listOf()))

    private var searchJob: Job? = null

    fun searchRequested(filter: String, query: String) {
        searchUiState.value = searchUiState.value.copy(isLoading = true)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if (query.isNotBlank()) {
                when (filter) {
                    "Track" -> searchByQuery(
                        getSearchUseCase = getTrackSearchUseCase,
                        mapper = { it.toSearchResultModel() },
                        query = query
                    )
                    "Album" -> searchByQuery(
                        getAlbumSearchUseCase, { it.toSearchResultModel() }, query
                    )
                    "Artist" -> searchByQuery(
                        getArtistSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Playlist" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Podcast" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Radio" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                }
            }
        }
    }

    private suspend fun <T> searchByQuery(
        getSearchUseCase: SuspendWithInputUseCase<String, Flow<Resource<List<T>>>>,
        mapper: (T) -> SearchResultModel,
        query: String
    ) {
        getSearchUseCase.invoke(query).collect { result ->
            when (result) {
                is Resource.Success -> {
                    searchUiState.value = searchUiState.value.copy(
                        isLoading = false,
                        searchResult = result.data.map { mapper(it) })
                }
                is Resource.Error -> {
                    searchUiState.value =
                        searchUiState.value.copy(isLoading = false, searchResult = emptyList())
                }
            }
        }
    }

    private fun Track.toSearchResultModel(): SearchResultModel = SearchResultModel(
        title = title,
        subTitle = artist.name,
        picture = cover,
    )

    private fun Album.toSearchResultModel(): SearchResultModel = SearchResultModel(
        title = name,
        subTitle = id.toString(),
        picture = cover,
    )

    private fun Artist.toSearchResultModel(): SearchResultModel = SearchResultModel(
        title = name,
        subTitle = link,
        picture = cover,
    )
}