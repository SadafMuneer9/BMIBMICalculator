package android.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalculate : Button
    private lateinit var height : EditText
    private lateinit var weight : EditText
    private lateinit var name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //Good Work
        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()

        btnCalculate = findViewById<Button>(R.id.btnCalculate)
        height = findViewById<EditText>(R.id.Height)
        weight = findViewById<EditText>(R.id.Weight)
        name = findViewById<EditText>(R.id.name)


        btnCalculate.setOnClickListener {

            val heightInFloat = height.text.toString().toFloat()
            val calculateHeight = heightInFloat * heightInFloat //height square

            val weight : Float = weight.text.toString().toFloat()

            val BMI = weight / calculateHeight

            Toast.makeText(this, "Calculated BMI $BMI", Toast.LENGTH_SHORT).show()

            moveToOutPutScreen(name.text.toString(), BMI)
        }
    }

    private fun moveToOutPutScreen(name: String, bmi: Float){

        val intent = Intent(this, OutputScreen::class.java)

        intent.putExtra("userName",name)
        intent.putExtra("userBMI",bmi)

        startActivity(intent)

    }
}
