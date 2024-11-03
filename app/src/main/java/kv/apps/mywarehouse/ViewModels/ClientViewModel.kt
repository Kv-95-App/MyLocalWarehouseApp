package kv.apps.mywarehouse.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kv.apps.mywarehouse.ClientsDB.Client
import kv.apps.mywarehouse.ClientsDB.ClientRepository

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {
    val allClients: LiveData<List<Client>> = repository.allClients

    fun insert(client: Client) {
        viewModelScope.launch {
            repository.insert(client)
        }
    }

    fun delete(client: Client) {
        viewModelScope.launch {
            repository.delete(client)
        }
    }

    fun update(client: Client) {
        viewModelScope.launch {
            repository.update(client)
        }
    }
}
