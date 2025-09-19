package com.toothlonely.kasperskydictionaryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class FavoritesViewModel() : ViewModel() {
    private val _favoritesLiveData = MutableLiveData<FavoritesState>()
    val favoritesLiveData: LiveData<FavoritesState>
        get() = _favoritesLiveData

    data class FavoritesState(
        val isFavoritesListVisible: Boolean = false,
        val favoritesDataSet: List<History> = listOf(),
    )

    init {
        initFavorites()
    }

    fun reloadFavorites() {
        initFavorites()
    }

    private fun initFavorites() {
        _favoritesLiveData.value = FavoritesState(
            isFavoritesListVisible = STUB.getFavorites().isNotEmpty(),
            //favoritesDataSet = STUB.getFavorites()
        )
    }

    fun openMainFragment(fragment: FavoritesFragment) {
        fragment.findNavController().navigate(R.id.action_favoritesFragment_to_mainFragment)
    }

    fun deleteFromFavorites(word: String) {
        STUB.deleteFromFavorites(word)

        _favoritesLiveData.value = _favoritesLiveData.value?.copy(
            isFavoritesListVisible = STUB.getFavorites().isNotEmpty(),
            //favoritesDataSet = STUB.getFavorites()
        )
    }
}