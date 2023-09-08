package com.athimue.usecase.implementation

import com.athimue.domain.model.Album
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.usecase.GetAlbumSearchUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetAlbumSearchUseCase {

    override suspend fun invoke(input: String): Flow<Resource<List<Album>>> =
        searchRepository.getAlbumSearch(input)

}