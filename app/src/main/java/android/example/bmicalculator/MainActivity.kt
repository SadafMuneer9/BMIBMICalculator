package android.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val Height=findViewById<EditText>(R.id.Height)
        val Weight=findViewById<EditText>(R.id.Weight)
        val result=findViewById<TextView>(R.id.result)
        btnCalculate.setOnClickListener{
            val h: Float =Height.text.toString().toFloat()/100
            val w: Float=Weight.text.toString().toFloat()
            val res:Float=(w/h*h)
            result.text=".2f".format(res)
        }
    }
}