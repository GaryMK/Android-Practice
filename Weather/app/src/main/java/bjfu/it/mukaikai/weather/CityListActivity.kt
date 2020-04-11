package bjfu.it.mukaikai.weather

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import bjfu.it.mukaikai.weather.utils.CityHelper
import bjfu.it.mukaikai.weather.utils.LeakCanaryUtils
import kotlinx.android.synthetic.main.activity_city_list.*

class CityListActivity : AppCompatActivity() {

    companion object {
        const val CITY_ID_RESULT_CODE = 1
        const val CITY_ID_KEY = "city_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        var cityNameList = CityHelper.getInstance().cityNameList
        list_view.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cityNameList)

        list_view.setOnItemClickListener(
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var cityId = CityHelper.getInstance().cityIdList.get(position)
                    var intent = Intent()
                    intent.putExtra(CITY_ID_KEY, cityId)
                    setResult(CITY_ID_RESULT_CODE, intent)
                    finish()

                }
            }
        )

        LeakCanaryUtils.addContext(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CityListActivity.CITY_ID_RESULT_CODE) {
            if (data != null) {
                var cityId = data.getStringArrayExtra(CityListActivity.CITY_ID_KEY)
            }
        }
    }
}
