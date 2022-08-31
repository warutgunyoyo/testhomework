package com.example.myapplication.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.MoneyTop
import com.example.myapplication.model.StateMentModel
import kotlinx.coroutines.*

class AccountViewModel : ViewModel() {
    var moneyTops=MutableLiveData<List<MoneyTop>>()

        private val viewModelJob= SupervisorJob()
            private val accountScope= CoroutineScope(Dispatchers.Main+viewModelJob)
    fun loadMoneyTop(){
            accountScope.launch(Dispatchers.IO){
                delay(1000)
                val list= listOf(MoneyTop("XXXX-XXXX-5511","Main",
                "57,000.00","47,599.00"),
                    MoneyTop("XXXX-XXXX-5522","Main",
                        "56,000.00","47,599.00"),
                    MoneyTop("XXXX-XXXX-5533","Main",
                        "58,000.00","47,599.00"),
                    MoneyTop("XXXX-XXXX-553ss3","Main",
                        "58,000.00","47,599.00"),




                )
                withContext(Dispatchers.Main){
                    moneyTops.value=list
                }
            }
    }

    var statementMoney=MutableLiveData<List<StateMentModel>>()
    private val viewModelJobStatement= SupervisorJob()
    private val accountScopeStateMent= CoroutineScope(Dispatchers.Main+viewModelJobStatement)
    fun loadStatement(){
        accountScopeStateMent.launch(Dispatchers.IO){
            delay(1000)
            val list= listOf(
                StateMentModel("transfer out",
                "20 Nov 2019 -12:00",
                "-1,500.00"),
                StateMentModel("transfer PromptPay",
                    "23 Nov 2019 -13:00",
                    "-2,500.00"),
                StateMentModel("transfer in",
                    "22 Nov 2019 -12:00",
                    "+3,500.00"),







                )
            withContext(Dispatchers.Main){
                statementMoney.value=list
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is account Fragment"
    }
    val text: LiveData<String> = _text
}