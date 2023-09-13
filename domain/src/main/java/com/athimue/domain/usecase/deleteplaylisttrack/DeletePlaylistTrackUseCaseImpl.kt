package com.athimue.domain.usecase.deleteplaylisttrack

import com.athimue.domain.repository.PlaylistRepository
import javax.inject.Inject

class DeletePlaylistTrackUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : DeletePlaylistTrackUseCase {

    override suspend fun invoke(firstInput: Int, secondInput: Long) {
        playlistRepository.deleteTrack(playlistId = firstInput, trackId = secondInput)
    }
}