package kv.apps.mywarehouse.productsDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Update
    suspend fun update(product: Product)
}
