package com.cabify.cabifymobilechallengexml.domain

class CabifyProductBo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float
)

enum class ProductCode {
    VOUCHER,
    TSHIRT,
    MUG,
    NONE
}