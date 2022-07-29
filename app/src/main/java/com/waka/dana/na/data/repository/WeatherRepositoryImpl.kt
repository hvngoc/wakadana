package com.waka.dana.na.data.repository

import com.google.gson.Gson
import com.waka.dana.na.data.services.WeatherServices
import com.waka.dana.na.domain.model.WeatherList
import com.waka.dana.na.domain.repository.WeatherRepository

/**
 * Created by hvngoc on 7/29/22
 */
class WeatherRepositoryImpl(
    gson: Gson,
    private val weatherServices: WeatherServices
) :
    BaseRepository(gson), WeatherRepository {

    @Throws(Exception::class)
    override fun getWeather(query: String?): WeatherList {
        val raw = weatherServices.getListing(query = query).execute()
        val data = networkTransform(raw)
        return data
    }
}