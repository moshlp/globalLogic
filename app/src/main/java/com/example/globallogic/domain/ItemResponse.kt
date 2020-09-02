package com.example.globallogic.domain

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class ItemResponse (
    var title : String? = null,
    var description : String? = null,
    var image : String? = null
) : Parcelable