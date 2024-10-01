package com.example.nfccard.repository

import android.content.Context
import com.example.nfccard.data.DataStoreManager
import com.example.nfccard.model.PassEvent
import kotlinx.coroutines.flow.Flow

class PassHistoryRepository(context: Context) {

    private val dataStoreManager = DataStoreManager.getInstance(context)

    suspend fun addPassEvent(event: PassEvent) {
        dataStoreManager.addPassEvent(event)
    }

    fun getPassHistoryFlow(): Flow<List<PassEvent>> {
        return dataStoreManager.getPassHistory()
    }
}
