package android.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class SavedBmiInfo : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_bmi)


        val bmiList = intent.extras?.getParcelableArrayList<BMI>("bmiList")


        recyclerView = findViewById(R.id.recyclerView)

        val adapter = BmiListAdapter(bmiList as ArrayList<BMI>)

        recyclerView.adapter = adapter

    }
}