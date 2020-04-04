package bjfu.it.mukaikai.weather.bean

data class SKWeather (
    val weatherinfo: WeatherInfo) {
    data class WeatherInfo (
        var temp: String,
        var WD: String,
        var WS: String,
        var SD: String,
        var AP: String
        )
}