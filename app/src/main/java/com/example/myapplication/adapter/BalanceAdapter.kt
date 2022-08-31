package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.MoneyTop

class BalanceAdapter(private val balanceList:List<MoneyTop>):RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder>() {

    class BalanceViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val usernumber:TextView=itemView.findViewById(R.id.user_number_value)
        val saveMethod:TextView=itemView.findViewById(R.id.user_save_method_value)
        val curentBalance:TextView=itemView.findViewById(R.id.curent_balance_value)
        val available:TextView=itemView.findViewById(R.id.available_value)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.adapter_top_item,parent,false)
        return BalanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
       val balance=balanceList[position]
        holder.usernumber.text=balance.userNumber
        holder.saveMethod.text=balance.saveMethod
        holder.curentBalance.text=balance.currentBalance
        holder.available.text=balance.availableBalance


    }

    override fun getItemCount(): Int {
       return balanceList.size
    }
}