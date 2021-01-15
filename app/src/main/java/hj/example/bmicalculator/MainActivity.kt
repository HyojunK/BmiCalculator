package hj.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 저장된 값 불러오기
        loadData()

        resultButton.setOnClickListener {
            // val intent = Intent(this, ResultActivity::class.java)
            // intent.putExtra("weight", weightEditText.text.toString())
            // intent.putExtra("height", heightEditText.text.toString())
            // startActivity(intent)

            // 마지막에 입력한 내용 저장
            saveData(heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt())

            // Anko 라이브러리 사용
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )

        }
    }

    //데이터 저장
    private fun saveData(height: Int, weight: Int) {
        // 프리퍼런스 객체 생성
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        // 프리퍼런스 에디터 생성
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    // 데이터 불러오기
    private fun loadData() {
        // 프리퍼런스 객체 생성
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        // 저장 된 값 불러오기(저장된 값이 없을 때 기본값 0을 리턴)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }

    }
}
