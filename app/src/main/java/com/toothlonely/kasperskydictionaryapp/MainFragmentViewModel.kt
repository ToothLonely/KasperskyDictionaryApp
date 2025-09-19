package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val _mainFragmentLiveData = MutableLiveData<MainFragmentState>()
    val mainFragmentLiveData: LiveData<MainFragmentState>
        get() = _mainFragmentLiveData

    private val repository = WordsRepository()

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
        viewModelScope.launch {
            val translation = withContext(Dispatchers.IO) {
                repository.getWord(englishWord)
            } ?: getString(application, R.string.not_in_dictionary)

            addWordInHistory(englishWord.lowercase())

            _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
                originalWord = englishWord, translate = translation,
                isHistoryVisible = true, historyDataSet = getHistoryList()
            )
        }

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
            isHistoryVisible = getHistoryList().isNotEmpty(),
            historyDataSet = getHistoryList()
        )
    }

    fun addWordInFavorites() {

        val currentText = _mainFragmentLiveData.value?.originalWord

        val toastStringNull = getString(application, R.string.toast_word_is_null)
        val toastStringInFavorites = getString(application, R.string.toast_word_is_in_favorites)
        val toastStringNotInDictionary =
            getString(application, R.string.toast_word_is_not_in_dictionary)

        viewModelScope.launch {
            when {
                currentText.isNullOrEmpty() -> Toast.makeText(
                    application, toastStringNull, Toast.LENGTH_SHORT
                ).show()

                withContext(Dispatchers.IO) {
                    repository.getWord(currentText)
                } != _mainFragmentLiveData.value?.translate -> Toast.makeText(
                    application, toastStringNull, Toast.LENGTH_SHORT
                ).show()

                withContext(Dispatchers.IO) {
                    repository.getWord(currentText)
                } == null -> Toast.makeText(
                    application, toastStringNotInDictionary, Toast.LENGTH_SHORT
                ).show()

                currentText in STUB.getFavorites() -> Toast.makeText(
                    application, toastStringInFavorites, Toast.LENGTH_SHORT
                ).show()

                else -> STUB.addInFavorites(currentText)
            }
        }
    }
}