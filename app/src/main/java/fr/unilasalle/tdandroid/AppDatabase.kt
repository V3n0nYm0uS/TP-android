package fr.unilasalle.tdandroid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.unilasalle.tdandroid.ProductDao
import fr.unilasalle.tdandroid.ProductEntity

@Database(entities = [ProductEntity::class, CartEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}
