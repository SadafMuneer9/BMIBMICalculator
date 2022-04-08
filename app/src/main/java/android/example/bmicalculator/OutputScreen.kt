package android.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OutputScreen : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var bmi: TextView
    private lateinit var reCalculate: Button
    private lateinit var bmiStatus: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_screen)

        name = findViewById(R.id.output_name)
        bmi = findViewById(R.id.tvResult)
        reCalculate = findViewById(R.id.reCalculate)
        val intent = intent

        name.text = intent.getStringExtra("userName")
        bmi.text = intent.getFloatExtra("userBMI", 0f).toString()

        reCalculate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun bmiStatusValue(bmi: Float): String {
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}