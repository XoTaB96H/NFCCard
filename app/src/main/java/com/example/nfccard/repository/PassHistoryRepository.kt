package com.example.nfccard.repository

import android.content.Context
import com.example.nfccard.data.DataStoreManager
import com.example.nfccard.model.PassEvent
import kotlinx.coroutines.flow.Flow

class PassHistoryRepository(context: Context) {

    private val dataStore = DataStoreManager(context)

    suspend fun addPassEvent(event: PassEvent) {
        dataStore.addPassEvent(event)
    }

    fun getPassHistoryFlow(): Flow<List<PassEvent>> {
        return dataStore.getPassHistory()
    }
}
