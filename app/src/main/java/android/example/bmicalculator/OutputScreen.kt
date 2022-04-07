package android.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class OutputScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output_screen)
        var result=findViewById<TextView>(R.id.result)
        
        result.text="%.2f".format(BMI)
        }
}