package android.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class OutputScreen : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var bmi: TextView
    private lateinit var reCalculate: Button


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

        }

    }
}