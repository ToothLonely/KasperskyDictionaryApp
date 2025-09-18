package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import androidx.navigation.fragment.findNavController

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val _mainFragmentLiveData = MutableLiveData<MainFragmentState>()
    val mainFragmentLiveData: LiveData<MainFragmentState>
        get() = _mainFragmentLiveData

    data class MainFragmentState(
        val originalWord: String? = null,
        val translate: String? = null,
        val isHistoryVisible: Boolean = false,
        val historyDataSet: List<String> = listOf(),
    )

    init {
        initMainFragment()
    }

    private fun initMainFragment() {
        _mainFragmentLiveData.value = MainFragmentState(
            isHistoryVisible = getHistoryList().isNotEmpty(),
            historyDataSet = getHistoryList()
        )
    }

    private fun getHistoryList(): List<String> {
        return STUB.getHistoryList()
    }

    fun searchWord(englishWord: String) {
        val translation = STUB.getTranslation(englishWord.lowercase()) ?: getString(
            application,
            R.string.not_in_dictionary
        )

        addWordInHistory(englishWord.lowercase())

        _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
            originalWord = englishWord, translate = translation,
            isHistoryVisible = true, historyDataSet = getHistoryList()
        )
    }

    private fun addWordInHistory(newWord: String) {
        STUB.addNewWordInHistory(newWord)
    }

    fun openFavoritesFragment(fragment: MainFragment) {
        fragment.findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
    }

    fun deleteWordFromHistory(word: String) {
        STUB.deleteWordFromHistory(word)

        _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
            isHistoryVisible =  getHistoryList().isNotEmpty(),
            historyDataSet = getHistoryList()
        )
    }

    fun addWordInFavorites(newWord: String?) {

        val toastStringNull = getString(application, R.string.toast_word_is_null)
        val toastStringInFavorites = getString(application, R.string.toast_word_is_in_favorites)
        val toastStringNotInDictionary =
            getString(application, R.string.toast_word_is_not_in_dictionary)

        when (newWord) {
            null -> Toast.makeText(
                application, toastStringNull, Toast.LENGTH_SHORT
            ).show()

            !in STUB.getOriginals() -> Toast.makeText(
                application, toastStringNotInDictionary, Toast.LENGTH_SHORT
            ).show()

            in STUB.getFavorites() -> Toast.makeText(
                application, toastStringInFavorites, Toast.LENGTH_SHORT
            ).show()

            else -> STUB.addInFavorites(newWord)
        }
    }
}