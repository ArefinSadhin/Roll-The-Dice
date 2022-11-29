package com.fizz.thegameofpig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fizz.thegameofpig.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private var TAG = "LoginPage"
    private lateinit var loginPageBinding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPageBinding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(loginPageBinding.root)

        loginPageBinding.buttonStart.setOnClickListener {
            val gameAct = Intent(this, MainActivity::class.java)
            var pOneName = loginPageBinding.editPlayerOne.text.toString()
            var pTwoName = loginPageBinding.editPlayerTwo.text.toString()
            if (pOneName == "" || pTwoName == "") {
                return@setOnClickListener
            } else {
                Log.d(TAG, "This line is working fine")
                gameAct.putExtra("playerOneName", pOneName)
                Log.d(TAG, pOneName)
                gameAct.putExtra("playerTwoName", pTwoName)
                startActivity(gameAct)
            }
        }
    }
}