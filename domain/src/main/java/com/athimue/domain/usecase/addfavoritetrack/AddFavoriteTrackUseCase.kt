package com.athimue.domain.usecase.addfavoritetrack

import com.athimue.domain.usecase.SuspendWithInputUseCase

interface AddFavoriteTrackUseCase : SuspendWithInputUseCase<Long, Unit> {
}