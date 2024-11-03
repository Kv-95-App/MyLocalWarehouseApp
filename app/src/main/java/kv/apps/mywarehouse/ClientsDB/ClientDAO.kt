package kv.apps.mywarehouse.ClientsDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClientDao {
    @Query("SELECT * FROM clients")
    fun getAllClients(): LiveData<List<Client>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(client: Client)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)
}
