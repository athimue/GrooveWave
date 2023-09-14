package com.athimue.domain.usecase.deletePlaylistTrack

import com.athimue.domain.usecase.SuspendTwoInputUseCase

interface DeletePlaylistTrackUseCase : SuspendTwoInputUseCase<Int, Long, Unit>