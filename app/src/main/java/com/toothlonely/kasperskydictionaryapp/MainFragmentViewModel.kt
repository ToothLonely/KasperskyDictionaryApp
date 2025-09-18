package com.toothlonely.kasperskydictionaryapp

import android.app.Application
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

        addNewWordInHistory(englishWord.lowercase())

        _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
            originalWord = englishWord, translate = translation,
            isHistoryVisible = true, historyDataSet = getHistoryList()
        )
    }

    private fun addNewWordInHistory(newWord: String) {
        STUB.addNewWordInHistory(newWord)
    }

    fun openFavoritesFragment(fragment: MainFragment) {
        fragment.findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
    }

    fun deleteWordFromHistory(word: String) {
        STUB.deleteWordFromHistory(word)

        _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
            historyDataSet = getHistoryList()
        )
    }
}