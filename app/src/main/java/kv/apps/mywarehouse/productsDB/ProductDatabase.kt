package kv.apps.mywarehouse.productsDB

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kv.apps.mywarehouse.ClientsDB.Client
import kv.apps.mywarehouse.ClientsDB.ClientDao


@Database(entities = [Product::class, Client::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun clientDao(): ClientDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "warehouse_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
