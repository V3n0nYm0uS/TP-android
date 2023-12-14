package fr.unilasalle.tdandroid

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "carts",
    foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = ["id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.CASCADE // Optional: Define the behavior on deletion
    )],
    indices = [Index("productId")] // Create an index on the productId column
)
data class CartEntity(
    @PrimaryKey val id: Int,
    val productId: Int, // Reference to the ProductEntity
    var quantity: Double
)