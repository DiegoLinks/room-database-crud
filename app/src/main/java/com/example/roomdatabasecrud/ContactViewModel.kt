package com.example.roomdatabasecrud

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    init {
        val contactDAO = AppDatabase.getDatabase(application, scope).contactDAO()
        repository = ContactRepository(contactDAO)
        allContacts = repository.allContacts
    }

    fun insert(contact: Contact) = scope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }

    fun delete(id: Int) = scope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun change(contact: Contact) = scope.launch(Dispatchers.IO) {
        repository.change(contact)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}