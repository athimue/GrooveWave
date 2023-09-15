package com.athimue.domain.usecase.deleteFavoriteTrack

import com.athimue.domain.usecase.SuspendOneInputUseCase

interface DeleteFavoriteTrackUseCase : SuspendOneInputUseCase<Long, Unit>