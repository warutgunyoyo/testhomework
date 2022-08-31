package com.example.myapplication.model

class StateMentModel constructor(
    val transfer_type:String,
    val dateTime_transfer:String,
    val amount:String,
) {
    override fun toString(): String {
        return amount
    }
}