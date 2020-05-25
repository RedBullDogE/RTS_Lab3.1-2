package com.example.rts_lab3_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeoutException


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

        val inputData3_a: EditText = findViewById(R.id.inputA)
        val inputData3_b: EditText = findViewById(R.id.inputB)
        val inputData3_c: EditText = findViewById(R.id.inputC)
        val inputData3_d: EditText = findViewById(R.id.inputD)
        val inputData3_y: EditText = findViewById(R.id.inputY)
        val goButton3: Button = findViewById(R.id.button3)
        val result3: TextView = findViewById(R.id.result3)

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
                val answer: String = factorization(value).contentToString()
                result1.text = answer.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    "Please, enter correct values!",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: TimeoutException) {
                Toast.makeText(
                    applicationContext,
                    "Time out :(",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        goButton2.setOnClickListener { _ ->
            val perceptron: Perceptron = Perceptron()
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

        goButton3.setOnClickListener { _ ->
            val a: Int
            val b: Int
            val c: Int
            val d: Int
            val y: Int

            try {
                a = inputData3_a.text.toString().toInt()
                b = inputData3_b.text.toString().toInt()
                c = inputData3_c.text.toString().toInt()
                d = inputData3_d.text.toString().toInt()
                y = inputData3_y.text.toString().toInt()
                try {
                    val answer = geneticAlgorithm(a, b, c, d, y).toString()
                    result3.text = answer
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(
                        applicationContext,
                        "Well... Not today :c",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    "Please, enter correct values!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)



