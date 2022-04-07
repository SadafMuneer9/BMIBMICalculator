package android.example.bmicalculator

import android.content.Intent
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
        var btnCalculate = findViewById<Button>(R.id.btnCalculate)
        var height=findViewById<EditText>(R.id.Height)
        var weight=findViewById<EditText>(R.id.Weight)
        var result=findViewById<TextView>(R.id.result)

        btnCalculate.setOnClickListener {
            val h: Float =height.text.toString().toFloat()/100
            val w: Float=weight.text.toString().toFloat()
            val BMI:Float=(w/h*h)
            val intent= Intent(this,OutputScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}
