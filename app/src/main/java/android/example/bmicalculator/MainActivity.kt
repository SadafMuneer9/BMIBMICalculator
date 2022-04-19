package android.example.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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
    private var conversionVal = 2  // 0 = CM , 1 = inch , 2= Mt

    companion object {
        val listBmi = ArrayList<BMI>()
    }

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

        val savedRadioButtonState = getSavedRadioButtonState()
        setRadioCheckedState(savedRadioButtonState)
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

                checkedId = radioGroup.checkedRadioButtonId
                conversionVal = findConversionVal(checkedId)
                saveRadioButtonState(conversionVal)
            }
        })


        btnCalculate.setOnClickListener {

            if (name.text.isNullOrEmpty() || weight.text.isNullOrEmpty() || height.text.isNullOrEmpty()) {
                return@setOnClickListener
            }
            val heightInFloat = height.text.toString().toFloat()

            val finalHeight = findConversionMultiplier(conversionVal, heightInFloat)


            //formula in meters
            val calculateHeight = finalHeight * finalHeight

            val weight: Float = weight.text.toString().toFloat()

            val BMI = weight / calculateHeight

            val roundoff = (BMI * 100.0).roundToInt() / 100.0

            Toast.makeText(this, "Calculated BMI $BMI", Toast.LENGTH_SHORT).show()

           moveToOutPutScreen(name.text.toString(), bmi=BMI)
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(MainActivity.toString(), "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setRadioCheckedState(savedState: Int) {
        if (savedState == 0) {
            radioCm.isChecked = true
            findConversionVal(R.id.radioCm)
        } else if (savedState == 1) {
            radioInch.isChecked = true
            findConversionVal(R.id.radioInch)
        } else {
            radioMt.isChecked = true
            findConversionVal(R.id.radioMt)
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

        return absoluteHeight
    }

    private fun moveToOutPutScreen(name: String, bmi: Float) {

        val intent = Intent(this, OutputScreen::class.java)

        intent.putExtra("userName", name)
        intent.putExtra("userBMI", bmi)
        intent.putExtra("bmiStatusValue", bmi)
        startActivity(intent)
        Toast.makeText(this,bmi.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, listBmi.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun saveRadioButtonState(radioButton: Int) {
        val preference = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt("radioButtonState", radioButton).apply()
    }

    private fun getSavedRadioButtonState(): Int {
        val preference = getSharedPreferences("pref", Context.MODE_PRIVATE)
        return preference.getInt("radioButtonState", 2)
    }

}