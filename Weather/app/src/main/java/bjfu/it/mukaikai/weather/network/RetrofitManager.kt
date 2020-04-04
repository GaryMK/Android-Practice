package bjfu.it.mukaikai.weather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitManager private constructor() {
    companion object {
        private var singleton: RetrofitManager? = null
        val instance: RetrofitManager?
            get() {
                if (singleton == null) {
                    singleton = RetrofitManager()
                }
                return singleton
            }
    }
    private val retrofitHashMap: HashMap<String, Retrofit>
    private fun createRetrofit(host: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit(host: String): Retrofit? {
        return if (retrofitHashMap.containsKey(host)) {
            retrofitHashMap[host]
        } else {
            val retrofit: Retrofit = createRetrofit(host)
            retrofitHashMap[host] = retrofit
            retrofit
        }
    }

    init {
        retrofitHashMap = HashMap()
    }
}