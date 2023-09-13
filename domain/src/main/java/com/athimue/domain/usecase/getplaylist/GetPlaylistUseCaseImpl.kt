package com.athimue.domain.usecase.getplaylist

import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : GetPlaylistUseCase {

    override suspend fun invoke(): Flow<List<Playlist>> =
        playlistRepository.getPlaylists()
}