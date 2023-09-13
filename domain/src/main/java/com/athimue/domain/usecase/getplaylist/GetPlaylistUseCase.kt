package com.athimue.domain.usecase.getplaylist

import com.athimue.domain.model.Playlist
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPlaylistUseCase : SuspendUseCase<Flow<List<Playlist>>>