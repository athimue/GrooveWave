package com.athimue.domain.usecase.deleteFavoriteAlbum

import com.athimue.domain.usecase.SuspendOneInputUseCase

interface DeleteFavoriteAlbumUseCase : SuspendOneInputUseCase<Long, Unit>