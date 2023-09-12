package com.athimue.usecase.implementation

import com.athimue.domain.model.Track
import com.athimue.domain.repository.FavoriteTracksRepository
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.usecase.GetFavoriteTracksUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteTracksUseCaseImpl @Inject constructor(
    private val favoriteTracksRepository: FavoriteTracksRepository,
    private val searchRepository: SearchRepository
) : GetFavoriteTracksUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): Flow<List<Track>> =
        favoriteTracksRepository.getFavorites().flatMapMerge { trackIds ->
            val trackFlows = trackIds.map { trackId ->
                searchRepository.getTrack(trackId).map {
                    when (it) {
                        is Resource.Success -> it.data
                        is Resource.Error -> {
                            null
                        }
                    }
                }
            }

            combine(trackFlows) { tracks ->
                tracks.filterNotNull().toList()
            }
        }
}