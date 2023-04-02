package com.waka.dana.na.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hvngoc on 7/29/22
 */
data class DemoParam(
    @SerializedName("type")
    val type: String?
)