package com.example.sqlitedemo

import android.app.Activity
import android.os.Bundle
import android.util.Log

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dbUtil = DBUtil.getInstance(this)
        dbUtil.addCustomers(Customer(10,"Krishna",909090909))
        dbUtil.addCustomers(Customer(11,"Raviraj",898989888))
        dbUtil.addCustomers(Customer(12,"Aniket",342323122))

        var customers = dbUtil.getAllCustomers()
        for(eachCustomer in customers){
            Log.e("tag","${eachCustomer.toString()}")
        }
    }
}