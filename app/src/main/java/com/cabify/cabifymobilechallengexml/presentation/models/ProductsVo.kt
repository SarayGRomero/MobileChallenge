package com.cabify.cabifymobilechallengexml.presentation.models

import android.os.Parcel
import android.os.Parcelable


data class ProductsVo(
    val products: List<CabifyProductVo> = emptyList(),
    val totalPrice: Float = 0.0f
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(CabifyProductVo)?.toList() ?: emptyList(),
        parcel.readFloat(),
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(products)
        dest.writeFloat(totalPrice)
    }

    companion object CREATOR : Parcelable.Creator<ProductsVo> {
        override fun createFromParcel(parcel: Parcel): ProductsVo {
            return ProductsVo(parcel)
        }

        override fun newArray(size: Int): Array<ProductsVo?> {
            return arrayOfNulls(size)
        }
    }

}