package com.example.rts_lab3_12

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textEntry: EditText = findViewById(R.id.editText)
        val goButton1: Button = findViewById(R.id.button1)
        val result1: TextView = findViewById(R.id.result1)

        val inputData2: TextView = findViewById(R.id.inputData2)
        val goButton2: Button = findViewById(R.id.button2)
        val result2: TextView = findViewById(R.id.result2)

        inputData2.text = "${inputData2.text}" +
                "(0; 6), (1; 5), (3, 3); (2, 4)\n" +
                "P = 4\n" +
                "LR = 0.001\n" +
                "Time Deadline: 1.0\n" +
                "Iterations: 1000"

        goButton1.setOnClickListener { _ ->
            val value: Long
            try {
                value = textEntry.text.toString().toLong()
                val answer: String = factorization(value)
                    .contentToString()
                result1.text = answer.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    "Please, enter correct values!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        goButton2.setOnClickListener { _ ->
            val perceptron: Perceptron =
                Perceptron()
            val P = 4.0
            val lr = 0.001
            val iterNumber = 1000
            val timeDeadline = 1.0

            val res: Boolean = perceptron.train(
                arrayOf(
                    doubleArrayOf(0.0, 6.0),
                    doubleArrayOf(1.0, 5.0),
                    doubleArrayOf(3.0, 3.0),
                    doubleArrayOf(2.0, 4.0)
                ),
                P,
                lr,
                iterNumber,
                timeDeadline
            )
            result2.text = "Time: ${perceptron.time}ms\n" +
                    "Iterations: ${perceptron.iter}\n" +
                    "W1 = ${perceptron.w1.format(2)}, W2 = ${perceptron.w2.format(2)}"
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)



