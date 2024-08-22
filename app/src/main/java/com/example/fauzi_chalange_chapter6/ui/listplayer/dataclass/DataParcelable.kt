package com.example.fauzi_chalange_chapter6.ui.listplayer.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataParcelable(
    val string: String,
    val integer: Int,
): Parcelable