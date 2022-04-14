package android.example.bmicalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BmiListAdapter(
    private val items: ArrayList<BMI>,

) : RecyclerView.Adapter<BmiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_saved_bmi_info, parent, false)

        return BmiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BmiViewHolder, position: Int) {
        val currentItem = items[position]

        holder.bmi.text = currentItem.bmi.toString()
        holder.name.text = currentItem.name
        holder.bodyType.text = currentItem.bodyType

    }
}

class BmiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bmi: TextView = itemView.findViewById(R.id.bmiText)
    val name: TextView = itemView.findViewById(R.id.name)
    val bodyType: TextView = itemView.findViewById(R.id.bodyType)
}