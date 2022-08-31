package com.example.myapplication.model

class MoneyTop constructor(
    val userNumber:String,
     val saveMethod:String,
     val currentBalance:String,
     val availableBalance:String
) {
    override fun toString(): String {
        return availableBalance
    }
}