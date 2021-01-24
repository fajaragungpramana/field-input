package com.github.fajaragungpramana.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.fajaragungpramana.field.DrawablePosition
import com.github.fajaragungpramana.field.FieldInput

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fieldInput = findViewById<FieldInput>(R.id.field_input)

        fieldInput.setOnClickDrawableListener(DrawablePosition.END) {
            Log.d(MainActivity::class.simpleName, "Clicked!")
        }

        fieldInput.text
        fieldInput.text = "Type something here!"

        fieldInput.errorMessage = "Type Something error message here!"
    }

}