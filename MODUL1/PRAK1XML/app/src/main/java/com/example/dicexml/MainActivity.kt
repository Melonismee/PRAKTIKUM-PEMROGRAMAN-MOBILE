package com.example.dicexml
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dice1 = findViewById<ImageView>(R.id.dice1)
        val dice2 = findViewById<ImageView>(R.id.dice2)
        val button = findViewById<Button>(R.id.button)
        val result = findViewById<TextView>(R.id.tvResult)

        button.setOnClickListener {

            val value1 = (1..6).random()
            val value2 = (1..6).random()

            dice1.setImageResource(getDiceImage(value1))
            dice2.setImageResource(getDiceImage(value2))

            if (value1 == value2) {
                result.text = "Selamat, anda dapat dadu double!"
            } else {
                result.text = "Anda belum beruntung!"
            }
        }
    }

    private fun getDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}