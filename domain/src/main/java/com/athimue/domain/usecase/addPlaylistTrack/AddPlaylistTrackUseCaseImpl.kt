package com.athimue.domain.usecase.addPlaylistTrack

import com.athimue.domain.repository.PlaylistRepository
import javax.inject.Inject

class AddPlaylistTrackUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : AddPlaylistTrackUseCase {

    override suspend fun invoke(firstInput: Int, secondInput: Long) {
        playlistRepository.addTrack(firstInput, secondInput)
    }
}