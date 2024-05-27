package com.cabify.cabifymobilechallengexml.presentation.utils.extensions

import android.app.AlertDialog
import android.content.Context
import com.cabify.cabifymobilechallengexml.domain.models.CabifyPromotionBo

fun Context.showPromotionInfo(promotion: CabifyPromotionBo) {
    val builder = AlertDialog.Builder(this)

    builder.apply {
        setTitle(promotion.name)
        setMessage(promotion.description)
        setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
    }.create().show()
}