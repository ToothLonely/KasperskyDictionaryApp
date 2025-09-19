package com.toothlonely.kasperskydictionaryapp.data.history

import com.toothlonely.kasperskydictionaryapp.model.History

class HistoryRepository(private val historyDao: HistoryDao) {
    suspend fun addInHistory(newHistory: HistoryDBEntity) = historyDao.addInHistory(newHistory)

    suspend fun deleteFromHistory(id: Int) = historyDao.deleteFromHistory(id)

    suspend fun getHistory(): List<History> = historyDao.getHistory()

}