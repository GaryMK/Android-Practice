package bjfu.it.mukaikai.weather.bean

data class CityInfoWeather (var weatherinfo: WeatherInfo) {
    data class WeatherInfo(
        var weather: String
    )
}