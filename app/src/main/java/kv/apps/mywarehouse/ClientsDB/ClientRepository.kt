package kv.apps.mywarehouse.ClientsDB

import androidx.lifecycle.LiveData

class ClientRepository(private val clientDao: ClientDao) {
    val allClients: LiveData<List<Client>> = clientDao.getAllClients()

    suspend fun insert(client: Client) {
        clientDao.insert(client)
    }

    suspend fun delete(client: Client) {
        clientDao.delete(client)
    }

    suspend fun update(client: Client) {
        clientDao.update(client)
    }
}
