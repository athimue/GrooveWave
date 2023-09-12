package com.athimue.domain.usecase

import com.athimue.domain.usecase.definition.SuspendWithInputUseCase

interface AddFavoriteTrackUseCase : SuspendWithInputUseCase<Long, Unit> {
}