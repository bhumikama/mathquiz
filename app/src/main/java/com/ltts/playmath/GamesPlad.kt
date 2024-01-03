package com.ltts.playmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GamesPlad : AppCompatActivity() {

    lateinit var scoreText: TextView
    lateinit var lifeText: TextView
    lateinit var timeText: TextView

    lateinit var textQuestion: TextView
    lateinit var textAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var rightResult =0
    var userScore =  0
    var userLife = 3

    lateinit var timer : CountDownTimer
    var startTimerinMillis : Long = 50000
    var timeLeftinMillis = startTimerinMillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_plad)

        scoreText = findViewById(R.id.score_text)
        lifeText = findViewById(R.id.life_text)
        timeText = findViewById(R.id.time_text)

        textQuestion = findViewById(R.id.textQuestion)
        textAnswer = findViewById(R.id.textAnswer)

        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        magicNumbers()
        supportActionBar!!.setTitle("Add GAME")
        buttonOk.setOnClickListener(View.OnClickListener {

            val answer: String = textAnswer.text.toString()

            if (answer == "")
            {

                Toast.makeText(applicationContext,"pleast input number",Toast.LENGTH_LONG).show()
            }
            else{
                pausetimer()

                val number = answer.toInt()
                if(number == rightResult)
                {
                    userScore+=5
                    textQuestion.text= " Congratulations, you answered correct"
                    scoreText.text = userScore.toString()
                }
                else
                {
                    userLife--
                    textQuestion.text= " Sorry, you answered wrong"
                    lifeText.text = userLife.toString()
                }
            }

        })

        buttonNext.setOnClickListener(View.OnClickListener {
          pausetimer()
          resetTimer()
           magicNumbers()
            textAnswer.setText("")
        })

    }

    fun magicNumbers()
    {
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"
        rightResult = number1+number2
        setTimer()


    }

    fun setTimer()
    {
        timer = object : CountDownTimer(timeLeftinMillis,1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftinMillis = millisUntilFinished
                updateTimerText()

            }

            override fun onFinish() {
                pausetimer()
                resetTimer()
                updateTimerText()

                userLife--
                textQuestion.text= " Sorry, your time is up"
                lifeText.text = userLife.toString()
            }

        }.start()
    }

    private fun resetTimer() {
        timeLeftinMillis =startTimerinMillis
        updateTimerText()

    }

    private fun pausetimer() {
        timer.cancel()
    }

    private fun updateTimerText() {
       var remainingTime: Int = (timeLeftinMillis/1000).toInt()
        timeText.text = String.format(Locale.getDefault(), "%02d",remainingTime)

    }
}