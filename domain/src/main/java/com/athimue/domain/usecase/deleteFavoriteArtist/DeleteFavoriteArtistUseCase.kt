package com.athimue.domain.usecase.deleteFavoriteArtist

import com.athimue.domain.usecase.SuspendOneInputUseCase

interface DeleteFavoriteArtistUseCase : SuspendOneInputUseCase<Long, Unit>