package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val _favoritesLiveData = MutableLiveData<FavoritesState>()
    val favoritesLiveData: LiveData<FavoritesState>
        get() = _favoritesLiveData

    data class FavoritesState(
        val isFavoritesListVisible: Boolean = false,
        val favoritesDataSet: List<Favorites> = listOf(),
    )

    private val favoritesRepo = (application as App).favoritesRepository

    init {
        viewModelScope.launch {
            initFavorites()
        }
    }

    suspend fun initFavorites() {
        val favorites = favoritesRepo.getFavorites()

        _favoritesLiveData.value = FavoritesState(
            isFavoritesListVisible = favorites.isNotEmpty(),
            favorites
        )
    }

    fun openMainFragment(fragment: FavoritesFragment) {
        fragment.findNavController().navigate(R.id.action_favoritesFragment_to_mainFragment)
    }

    suspend fun deleteFromFavorites(id: Int) {
        favoritesRepo.deleteFromFavorites(id)

        val currentFavoritesList = favoritesRepo.getFavorites()

        _favoritesLiveData.value = _favoritesLiveData.value?.copy(
            isFavoritesListVisible = currentFavoritesList.isNotEmpty(),
            favoritesDataSet = currentFavoritesList,
        )
    }
}