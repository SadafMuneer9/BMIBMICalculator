package android.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalculate: Button
    private lateinit var height: EditText
    private lateinit var weight: EditText
    private lateinit var name: EditText
    private lateinit var heightText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioCm: RadioButton
    private lateinit var radioInch: RadioButton
    private lateinit var radioMt: RadioButton
    private var conversionVal = 2;   // 0 = CM , 1 = inch , 2= Mt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Good Work
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        btnCalculate = findViewById(R.id.btnCalculate)
        height = findViewById(R.id.Height)
        weight = findViewById(R.id.Weight)
        name = findViewById(R.id.name)
        radioGroup = findViewById(R.id.radioGroup)
        radioCm = findViewById(R.id.radioCm)
        radioInch = findViewById(R.id.radioInch)
        radioMt = findViewById(R.id.radioMt)
        heightText = findViewById(R.id.tvHeight)


        var checkedId: Int

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

                checkedId = radioGroup.checkedRadioButtonId
                conversionVal = findConversionVal(checkedId)
            }

        })

        Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show()

        btnCalculate.setOnClickListener {

            val heightInFloat = height.text.toString().toFloat()

            val finalHeight = findConversionMultiplier(conversionVal, heightInFloat)


            //formula in meters
            val calculateHeight = finalHeight * finalHeight

            val weight: Float = weight.text.toString().toFloat()

            val BMI = weight / calculateHeight

            val roundoff = (BMI * 100.0).roundToInt() / 100.0

            Toast.makeText(this, "Calculated BMI $BMI", Toast.LENGTH_SHORT).show()

            moveToOutPutScreen(name.text.toString(), roundoff.toFloat())
        }
    }

    private fun findConversionVal(checkedId: Int): Int {

        if (checkedId == R.id.radioCm) {
            heightText.text = getString(R.string.Height_cm)
            return 0
        } else if (checkedId == R.id.radioInch) {
            heightText.text = getString(R.string.Height_in)
            return 1
        }
        heightText.text = getString(R.string.Height_mt)
        return 2
    }

    private fun findConversionMultiplier(conversionVal: Int, actualHeight: Float): Float {

        val absoluteHeight: Float

        if (conversionVal == 0) {

            //cm

            absoluteHeight = actualHeight / 100


        } else if (conversionVal == 1) {

            //inch

            absoluteHeight = (actualHeight / 39.37).toFloat()

        } else {
            absoluteHeight = actualHeight
        }

        return absoluteHeight;
    }

    private fun moveToOutPutScreen(name: String, bmi: Float) {

        val intent = Intent(this, OutputScreen::class.java)

        intent.putExtra("userName", name)
        intent.putExtra("userBMI", bmi)
        intent.putExtra("bmiStatusValue", bmi)
        startActivity(intent)

    }


}