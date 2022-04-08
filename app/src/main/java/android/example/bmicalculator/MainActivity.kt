package android.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalculate: Button
    private lateinit var height: EditText
    private lateinit var weight: EditText
    private lateinit var name: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioCm: RadioButton
    private lateinit var radioInch: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Good Work
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        btnCalculate = findViewById(R.id.btnCalculate)
        height = findViewById(R.id.Height)
        weight = findViewById(R.id.Weight)
        name = findViewById(R.id.name)
        radioGroup=findViewById(R.id.radioGroup)
        radioCm=findViewById(R.id.radioCm)
        radioInch=findViewById(R.id.radioInch)
        btnCalculate.setOnClickListener {

            val heightInFloat = height.text.toString().toFloat()
            val calculateHeight = heightInFloat * heightInFloat //height square

            val weight: Float = weight.text.toString().toFloat()

            val BMI = weight / calculateHeight

            Toast.makeText(this, "Calculated BMI $BMI", Toast.LENGTH_SHORT).show()

            moveToOutPutScreen(name.text.toString(), BMI)
        }
    }

    private fun moveToOutPutScreen(name: String, bmi: Float) {

        val intent = Intent(this, OutputScreen::class.java)

        intent.putExtra("userName", name)
        intent.putExtra("userBMI", bmi)
        intent.putExtra("bmiStatusValue", bmi)
        startActivity(intent)

    }


}