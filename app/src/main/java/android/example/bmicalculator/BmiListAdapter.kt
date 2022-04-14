package android.example.bmicalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BmiListAdapter(
    private val items: ArrayList<BMI>,
    private val listener:BmiItemClicked,
): RecyclerView.Adapter<BmiListAdapter.BmiViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmiViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_bmi,parent,false)
        val viewHolder=BmiViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return viewHolder
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: BmiViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.toString()

    }
    class BmiViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.title)
        }
    interface BmiItemClicked {

        fun onItemClicked(item: BMI)
    }}
