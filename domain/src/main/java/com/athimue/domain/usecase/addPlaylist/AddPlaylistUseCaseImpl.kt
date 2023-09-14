package com.athimue.domain.usecase.addPlaylist

import com.athimue.domain.repository.PlaylistRepository
import javax.inject.Inject

class AddPlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : AddPlaylistUseCase {
    override suspend fun invoke(input: String) {
        playlistRepository.addPlaylist(input)
    }
}