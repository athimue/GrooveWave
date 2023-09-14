package com.athimue.domain.usecase.getAlbumInfo

import com.athimue.domain.repository.SearchRepository
import javax.inject.Inject

class GetAlbumInfoUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetAlbumInfoUseCase {

    override suspend fun invoke(input: Long) =
        searchRepository.getAlbum(input)
}