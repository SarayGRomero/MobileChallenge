package com.cabify.cabifymobilechallengexml.domain

import android.os.Parcel
import android.os.Parcelable

class CabifyProductBo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float
)

enum class ProductCode() : Parcelable {
    VOUCHER,
    TSHIRT,
    MUG,
    NONE;

    override fun describeContents() = 0
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<ProductCode> {
        override fun createFromParcel(parcel: Parcel): ProductCode {
            return valueOf(parcel.readString() ?: "NONE")
        }

        override fun newArray(size: Int): Array<ProductCode?> {
            return arrayOfNulls(size)
        }
    }
}