package com.athimue.domain.usecase.getplaylists

import com.athimue.domain.model.Playlist
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPlaylistsUseCase : SuspendUseCase<Flow<List<Playlist>>>