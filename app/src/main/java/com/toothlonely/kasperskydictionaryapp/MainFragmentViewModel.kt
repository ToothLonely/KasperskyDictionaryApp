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

    private val historyRepo = (application as App).historyRepository

    data class MainFragmentState(
        val originalWord: String? = null,
        val translate: String? = null,
        val isHistoryVisible: Boolean = false,
        val historyDataSet: List<History> = listOf(),
    )

    init {
        viewModelScope.launch {
            initMainFragment()
        }
    }

    private suspend fun initMainFragment() {
        _mainFragmentLiveData.value = MainFragmentState(
            isHistoryVisible = getHistoryList().isNotEmpty(),
            historyDataSet = getHistoryList()
        )
    }

    private suspend fun getHistoryList(): List<History> {
        return withContext(Dispatchers.IO) {
            historyRepo.getHistory()
        }
    }

    fun searchWord(englishWord: String) {
        viewModelScope.launch {
            val translation = withContext(Dispatchers.IO) {
                repository.getWord(englishWord)
            } ?: getString(application, R.string.not_in_dictionary)

            addWordInHistory(englishWord.lowercase())

            _mainFragmentLiveData.value = MainFragmentState(
                originalWord = englishWord, translate = translation,
            )
        }
    }

    private fun addWordInHistory(newWord: String) {
        viewModelScope.launch {
            historyRepo.addInHistory(HistoryDBEntity(word = newWord))

            _mainFragmentLiveData.value = _mainFragmentLiveData.value?.copy(
                isHistoryVisible = getHistoryList().isNotEmpty(),
                historyDataSet = getHistoryList()
            )
        }
    }

    fun openFavoritesFragment(fragment: MainFragment) {
        fragment.findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
    }

    suspend fun deleteWordFromHistory(id: Int) {
        historyRepo.deleteFromHistory(id)

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
        val toastStringEnterClick = getString(application, R.string.toast_click_enter)

        viewModelScope.launch {
            when {
                currentText.isNullOrEmpty() -> Toast.makeText(
                    application, toastStringNull, Toast.LENGTH_SHORT
                ).show()

                withContext(Dispatchers.IO) {
                    repository.getWord(currentText)
                } != _mainFragmentLiveData.value?.translate -> Toast.makeText(
                    application, toastStringEnterClick, Toast.LENGTH_SHORT
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