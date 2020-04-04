package bjfu.it.mukaikai.weather.network

import bjfu.it.mukaikai.weather.bean.CityInfoWeather
import bjfu.it.mukaikai.weather.bean.SKWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("data/cityinfo/{cityId}.html")
    fun getCityInfoWeather(@Path("cityId") cityId: String?): Call<CityInfoWeather?>?


    @GET("/data/sk/{cityId}.html")
    fun getSKWeather(@Path("cityId") cityId: String?): Call<SKWeather?>?
}