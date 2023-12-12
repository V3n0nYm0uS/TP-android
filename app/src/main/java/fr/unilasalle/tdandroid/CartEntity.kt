package fr.unilasalle.tdandroid

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "carts",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["categoryId"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE // Optional: Define the behavior on deletion
    )
    ])
data class CartEntity(
    @PrimaryKey val id: Int,

    val productEntity: ProductEntity,

    val capacity: Int
)
