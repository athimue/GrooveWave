package com.athimue.domain.usecase

import com.athimue.domain.model.Playlist
import com.athimue.domain.usecase.definition.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPlaylistUseCase : SuspendUseCase<Flow<List<Playlist>>>