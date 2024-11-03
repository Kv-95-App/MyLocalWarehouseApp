package kv.apps.mywarehouse.ClientsDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val phoneNumber: String
)
