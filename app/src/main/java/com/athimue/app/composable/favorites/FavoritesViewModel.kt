package com.athimue.app.composable.favorites

import androidx.lifecycle.ViewModel
import com.athimue.domain.repository.FavoriteAlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteAlbumsRepository: FavoriteAlbumsRepository
) : ViewModel() {
}