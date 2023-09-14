package com.athimue.domain.usecase.addPlaylist

import com.athimue.domain.usecase.SuspendOneInputUseCase
import com.athimue.domain.usecase.SuspendUseCase

interface AddPlaylistUseCase : SuspendOneInputUseCase<String, Unit> {
}