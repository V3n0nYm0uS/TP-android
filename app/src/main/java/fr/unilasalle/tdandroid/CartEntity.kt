package fr.unilasalle.tdandroid

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey val id: Int,

    @Embedded
    val productEntity: ProductEntity,

    val capacity: Int
)
