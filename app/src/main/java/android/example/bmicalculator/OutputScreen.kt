package android.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class OutputScreen : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var bmi: TextView
    private lateinit var reCalculate: Button
    private lateinit var bmiStatus: String
    private lateinit var bmiInfo: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_screen)

        name = findViewById(R.id.output_name)
        bmi = findViewById(R.id.tvResult)
        reCalculate = findViewById(R.id.reCalculate)
        bmiInfo = findViewById(R.id.bmiInfo)
        imageView = findViewById(R.id.bodyTypeImage)


        val intent = intent

        name.text = intent.getStringExtra("userName")
        val Bmi = intent.getFloatExtra("userBMI", 0f)
        bmi.text = Bmi.toString()

        bmiInfo.text = bmiStatusValue(Bmi)

        reCalculate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun bmiStatusValue(bmi: Float): String {
        if (bmi < 18.5) {
            imageView.setBackgroundResource(R.drawable.underweight)
            bmiStatus = "Underweight"
        } else if (bmi >= 18.5 && bmi < 25) {
            imageView.setBackgroundResource(R.drawable.normal)
            bmiStatus = "Normal"
        } else if (bmi >= 25 && bmi < 30) {
            imageView.setBackgroundResource(R.drawable.overweight)
            bmiStatus = "Overweight"
        } else if (bmi > 30) {
            imageView.setBackgroundResource(R.drawable.obese)
            bmiStatus = "Obese"
        }
        return bmiStatus
    }
}