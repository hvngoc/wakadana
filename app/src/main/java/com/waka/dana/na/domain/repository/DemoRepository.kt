package com.waka.dana.na.domain.repository

import com.waka.dana.na.domain.model.DemoResult

/**
 * Created by hvngoc on 7/29/22
 */
interface DemoRepository {

    @Throws(Throwable::class)
    fun getListDemo(): DemoResult

    @Throws(Throwable::class)
    fun putNewTypes(type: String): DemoResult
}