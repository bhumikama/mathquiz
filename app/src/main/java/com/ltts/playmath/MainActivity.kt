package com.ltts.playmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var add: Button
    lateinit var sub: Button
    lateinit var mul: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add= findViewById(R.id.add_button)
        sub= findViewById(R.id.sub_button)
        mul= findViewById(R.id.mul_button)
        add.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@MainActivity,GamesPlad::class.java)
            startActivity(intent)
        })

    }
}