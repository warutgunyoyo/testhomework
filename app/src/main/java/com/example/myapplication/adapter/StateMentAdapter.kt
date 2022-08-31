package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.StateMentModel

class StateMentAdapter(private val statementList:List<StateMentModel>):RecyclerView.Adapter<StateMentAdapter.StateMentViewHolder>() {

    class StateMentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val transfer_type_value:TextView=itemView.findViewById(R.id.transfer_type_value)
        val date_value:TextView=itemView.findViewById(R.id.date_value)
        val amount_vlue:TextView=itemView.findViewById(R.id.amount_vlue)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateMentViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.stement_item,parent,false)
        return StateMentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StateMentViewHolder, position: Int) {
        val statementData=statementList[position]
        holder.transfer_type_value.text=statementData.transfer_type
        holder.date_value.text=statementData.dateTime_transfer
        holder.amount_vlue.text=statementData.amount
        if (statementData.amount.contains("-")){
            holder.amount_vlue.setTextColor(ContextCompat.getColor(holder.amount_vlue.context, R.color.red_item))
        }else{
            holder.amount_vlue.setTextColor(ContextCompat.getColor(holder.amount_vlue.context, R.color.green_item))

        }


    }

    override fun getItemCount(): Int {
        return statementList.size
    }
}