package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context

class DBUtil (var context: Context) {
    companion object{
        var dbUtil : DBUtil? = null
        fun getInstance(context: Context) : DBUtil{
            if(dbUtil == null){
                dbUtil = DBUtil(context)
            }
            return dbUtil!!
        }
    }

    fun addCustomers(customer : Customer):Boolean{
        var db = ProductDBHelper(
            context,
            "db_customers",
            null,
            1
        ).writableDatabase

        var values = ContentValues()
        values.put("_id",customer.id)
        values.put("name",customer.name)
        values.put("phone",customer.phone)

        var rowNum = db.insert(
            "customers",
            null,
            values
        )

        db.close()
        return rowNum >= 0
    }


    fun getAllCustomers() : ArrayList<Customer>{
        var db = ProductDBHelper(
            context,
            "db_customers",
            null,
            1
        ).writableDatabase

        var customers = ArrayList<Customer>()
        var c = db.query(
            "customers",
            null,null,null,null,null,null
        )

        while (c.moveToNext()){
            customers.add(
                Customer(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2)
                )
            )
        }

        db.close()
        return customers
    }
}