package com.cabify.cabifymobilechallengexml.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class CabifyProductBo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float
)

@Parcelize
enum class ProductCode : Parcelable {
    VOUCHER,
    TSHIRT,
    MUG,
    NONE
}