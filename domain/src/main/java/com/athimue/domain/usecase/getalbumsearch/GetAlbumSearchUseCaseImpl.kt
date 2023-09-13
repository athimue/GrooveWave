package com.athimue.domain.usecase.getalbumsearch

import com.athimue.domain.model.Album
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetAlbumSearchUseCase {

    override suspend fun invoke(input: String): Flow<Result<List<Album>>> =
        searchRepository.getAlbums(input)

}