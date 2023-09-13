package com.athimue.domain.usecase.getplaylistinfo

import com.athimue.domain.model.Playlist
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetPlaylistInfoUseCase : SuspendOneInputUseCase<Int, Flow<Playlist>>