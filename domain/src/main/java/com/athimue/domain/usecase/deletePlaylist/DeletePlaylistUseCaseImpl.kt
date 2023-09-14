package com.athimue.domain.usecase.deletePlaylist

import com.athimue.domain.repository.PlaylistRepository
import javax.inject.Inject

class DeletePlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : DeletePlaylistUseCase {
    override suspend fun invoke(input: Int) {
        playlistRepository.deletePlaylist(input)
    }
}