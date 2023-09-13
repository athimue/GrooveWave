package com.athimue.domain.usecase.deleteplaylisttrack

import com.athimue.domain.usecase.SuspendTwoInputUseCase

interface DeletePlaylistTrackUseCase : SuspendTwoInputUseCase<Int, Long, Unit>