package com.athimue.domain.usecase.getPlaylists

import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : GetPlaylistsUseCase {

    override suspend fun invoke(): Flow<List<Playlist>> =
        playlistRepository.getPlaylists()
}