package com.athimue.domain.usecase.addFavoriteArtist

import com.athimue.domain.usecase.SuspendOneInputUseCase

interface AddFavoriteArtistUseCase : SuspendOneInputUseCase<Long, Unit>