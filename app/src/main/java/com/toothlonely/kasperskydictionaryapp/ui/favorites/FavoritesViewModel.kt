package com.toothlonely.kasperskydictionaryapp.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.toothlonely.kasperskydictionaryapp.App
import com.toothlonely.kasperskydictionaryapp.R
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesRepository
import com.toothlonely.kasperskydictionaryapp.model.Favorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    application: Application,
    private val favoritesRepo: FavoritesRepository,
) : AndroidViewModel(application) {
    private val _favoritesLiveData = MutableLiveData<FavoritesState>()
    val favoritesLiveData: LiveData<FavoritesState>
        get() = _favoritesLiveData

    data class FavoritesState(
        val isFavoritesListVisible: Boolean = false,
        val favoritesDataSet: List<Favorites> = listOf(),
    )

    init {
        viewModelScope.launch {
            initFavorites()
        }
    }

    fun initFavorites() {
        viewModelScope.launch {
            val favorites = favoritesRepo.getFavorites()

            _favoritesLiveData.value = FavoritesState(
                isFavoritesListVisible = favorites.isNotEmpty(),
                favorites
            )
        }
    }

    fun openMainFragment(fragment: FavoritesFragment) {
        fragment.findNavController().navigate(R.id.action_favoritesFragment_to_mainFragment)
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            favoritesRepo.deleteFromFavorites(id)

            val currentFavoritesList = favoritesRepo.getFavorites()

            _favoritesLiveData.value = _favoritesLiveData.value?.copy(
                isFavoritesListVisible = currentFavoritesList.isNotEmpty(),
                favoritesDataSet = currentFavoritesList,
            )
        }
    }
}