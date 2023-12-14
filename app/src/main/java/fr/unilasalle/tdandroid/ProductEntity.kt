package fr.unilasalle.tdandroid

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products")
data class ProductEntity(
   @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String?,
    val category: String?,
    val image: String?,
   @Embedded
   val rating: RatingEntity?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(RatingEntity::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(image)
        parcel.writeParcelable(rating, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductEntity> {
        override fun createFromParcel(parcel: Parcel): ProductEntity {
            return ProductEntity(parcel)
        }

        override fun newArray(size: Int): Array<ProductEntity?> {
            return arrayOfNulls(size)
        }
    }
}

@Entity(tableName="rates")
data class RatingEntity(
 val rate: Double,
 val count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(rate)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RatingEntity> {
        override fun createFromParcel(parcel: Parcel): RatingEntity {
            return RatingEntity(parcel)
        }

        override fun newArray(size: Int): Array<RatingEntity?> {
            return arrayOfNulls(size)
        }
    }
}