package com.athimue.domain.usecase.addPlaylistTrack

import com.athimue.domain.usecase.SuspendTwoInputUseCase

interface AddPlaylistTrackUseCase : SuspendTwoInputUseCase<Int, Long, Unit>