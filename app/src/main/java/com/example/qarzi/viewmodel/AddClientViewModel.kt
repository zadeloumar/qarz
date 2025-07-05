package com.example.qarzi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.qarzi.data.DatabaseProvider
import com.example.qarzi.data.model.Client
import kotlinx.coroutines.launch

class AddClientViewModel(app: Application) : AndroidViewModel(app) {

    private val clientDao = DatabaseProvider.getDatabase(app).clientDao()

    fun addClient(client: Client, onSuccess: () -> Unit) {
        viewModelScope.launch {
            clientDao.insertClient(client)
            onSuccess()
        }
    }
}
