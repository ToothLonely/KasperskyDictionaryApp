package com.toothlonely.kasperskydictionaryapp

class HistoryRepository(private val historyDao: HistoryDao) {
    suspend fun addInHistory(newHistory: HistoryDBEntity) = historyDao.addInHistory(newHistory)

    suspend fun deleteFromHistory(id: Int) = historyDao.deleteFromHistory(id)

    suspend fun getHistory(): List<History> = historyDao.getHistory()

}