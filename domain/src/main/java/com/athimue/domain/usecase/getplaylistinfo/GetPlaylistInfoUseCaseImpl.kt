package com.athimue.domain.usecase.getplaylistinfo

import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistInfoUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : GetPlaylistInfoUseCase {

    override suspend fun invoke(input: Int): Flow<Playlist> =
        playlistRepository.getPlaylist(input)
}