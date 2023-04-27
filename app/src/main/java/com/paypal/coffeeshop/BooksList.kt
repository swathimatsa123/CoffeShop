package com.paypal.coffeeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class BooksList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Find fruit list view

        val listView: ListView = findViewById(R.id.listView);

        // Initialize fruit data
        val fruits = arrayOf<String>(
            "Apple",
            "Banana",
            "Cherry",
            "Dates",
            "Elderberry",
            "Fig",
            "Grapes",
            "Grapefruit",
            "Guava",
            "Jack fruit",
            "Lemon",
            "Mango",
            "Orange",
            "Papaya",
            "Pears",
            "Peaches",
            "Pineapple",
            "Plums",
            "Raspberry",
            "Strawberry",
            "Watermelon"
        );




        // Create Array adapter
        val adapter: ArrayAdapter<*> =  ArrayAdapter(
            this,
            R.layout.item, fruits)


        // Set adapter in list view
        listView.adapter=adapter
    }
}