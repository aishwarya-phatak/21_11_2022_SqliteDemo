package com.example.sqlitedemo

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqlitedemo.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var db : SQLiteDatabase
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        db = openOrCreateDatabase(
            "db_products",
            Activity.MODE_PRIVATE,
            null
        )
        try{
            if ("db_products" == null){
                db.execSQL("create table products(_id integer primary key,title text not null,price integer)")
            }
        } catch (e : Exception){
            Log.e("tag","exception is $e")
        }

        //Log.e("tag","Insert Operation on Database")
        //insertRecords()
        Log.e("tag","Retrive All Products")
        getProducts()
    }

    private fun getProducts(){

        //to fetch all Products - not preferred, select specific column related data
        var c = db.rawQuery("select * from products",
        null)

        //to fetch Products in specific price range
        /*var c = db.rawQuery(
            "select _id,title,price from products where price > ? and price < ?",
        arrayOf("1000","10000")
        )*/

        while (c.moveToNext()){
            var id = c.getInt(0)
            var title = c.getString(1)
            var price = c.getInt(2)
            Log.e("tag","$id -- $title -- $price")
        }

        c.close()

    }

    private fun updateRecords(){
        var values = ContentValues()
        values.put("price",9090)

        var count = db.update(
            "products",
            values,
            "_id = ?",
            arrayOf("55")
        )

        values = ContentValues()
        values.put("price",8989)
        values.put("title","One")

        db.update("products",
        values,
        "_id = ?",
        arrayOf("101"))
    }


    private fun insertRecords(){
        var values = ContentValues()
        values.put("_id",101)
        values.put("title","Product 1")
        values.put("price",1000)


        var rowNum = db.insert("products",null,values)
        if(rowNum >= 0){
            Log.e("tag","Record inserted successfully")
        } else {
            Log.e("tag","Record insertion failed")
        }

        var random = Random()
        for(i in 1..20){
            values.put("_id",Math.abs(random.nextInt()))
            values.put("title","Product $i")
            values.put("price",(Math.abs(random.nextInt()) % 1000) * 2)

            db.insert("products",null,values)
        }
    }














}