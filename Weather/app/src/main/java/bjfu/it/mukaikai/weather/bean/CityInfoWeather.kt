package bjfu.it.mukaikai.weather.bean

import androidx.annotation.Keep

@Keep
data class CityInfoWeather (var weatherinfo: WeatherInfo) {
    data class WeatherInfo(
        var weather: String
    )
}