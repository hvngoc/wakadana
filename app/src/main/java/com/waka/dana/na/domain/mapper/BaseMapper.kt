package com.waka.dana.na.domain.mapper

/**
 * Created by hvngoc on 9/27/22
 */
interface BaseMapper<FROM, TO> {
    fun fromEntity(from: FROM): TO

    fun toEntity(to: TO): FROM
}