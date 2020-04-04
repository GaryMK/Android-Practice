package bjfu.it.mukaikai.weather

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bjfu.it.mukaikai.weather.bean.CityInfoWeather
import bjfu.it.mukaikai.weather.bean.SKWeather
import bjfu.it.mukaikai.weather.network.ApiService
import bjfu.it.mukaikai.weather.network.RetrofitManager
import bjfu.it.mukaikai.weather.utils.CityHelper
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class WeatherActivity : AppCompatActivity() {

    companion object {
        const val HOST_WEATHER = "http://www.weather.com.cn"
        var cityId: String = "0"
        val apiService: ApiService? = RetrofitManager.instance?.getRetrofit(HOST_WEATHER)?.create(ApiService::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CityListActivity.CITY_ID_RESULT_CODE) {
            if (data != null) {
                var cityId = data.getStringExtra(CityListActivity.CITY_ID_KEY)
                refresh(cityId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        cityName.setOnClickListener {
            var intent = Intent( this@WeatherActivity, CityListActivity::class.java)
            startActivityForResult(intent, CityListActivity.CITY_ID_RESULT_CODE)
        }
        refresh(CityHelper.getDefaultCityId())

    }

    private fun refresh(cityId: String) {
        if (TextUtils.isEmpty(cityId) || cityId == WeatherActivity.cityId) {
            return
        }
        val cityInfoCall: Call<CityInfoWeather?>? = apiService?.getCityInfoWeather(cityId)
        cityInfoCall?.enqueue(object : Callback<CityInfoWeather?> {
            override fun onResponse(
                call: Call<CityInfoWeather?>,
                cityInfoResponse: Response<CityInfoWeather?>
            ) {
                val skCall: Call<SKWeather?>? = apiService?.getSKWeather(cityId)
                skCall?.enqueue(object : Callback<SKWeather?> {
                    override fun onResponse(
                        call: Call<SKWeather?>,
                        skResponse: Response<SKWeather?>
                    ) {
                        val cityInfoWeather: CityInfoWeather? = cityInfoResponse.body()
                        val skWeather: SKWeather? = skResponse.body()
                        cityName.text = getString(R.string.city_name_str, CityHelper.getInstance().cityInfoMap.get(cityId)?.name)
                        temp.text = getString(R.string.temp_str, skWeather?.weatherinfo?.temp)
                        wethear.text = cityInfoWeather?.weatherinfo?.weather
                        wd.text = getString(R.string.wd_str, skWeather?.weatherinfo?.WD)
                        ws.text = getString(R.string.ws_str, skWeather?.weatherinfo?.WS)
                        sd.text = getString(R.string.sd_str, skWeather?.weatherinfo?.SD)
                        ap.text = getString(R.string.ap_str, skWeather?.weatherinfo?.AP)
                    }

                    override fun onFailure(
                        call: Call<SKWeather?>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@WeatherActivity, "请求SK接口失败", Toast.LENGTH_LONG).show()
                    }
                })
            }

            override fun onFailure(
                call: Call<CityInfoWeather?>,
                t: Throwable
            ) {
                Toast.makeText(this@WeatherActivity, "请求CityInfo接口失败", Toast.LENGTH_LONG).show()
            }
        })
    }
}
