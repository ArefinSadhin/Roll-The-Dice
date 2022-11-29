package com.fizz.thegameofpig

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fizz.thegameofpig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var currentScore = 0
    private var activePlayer = 1
    private var dice = Dice(6)
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fetching name value from login class and setting it as my player name
        player1 = Player(intent.getStringExtra("playerOneName").toString())
        player2 = Player(intent.getStringExtra("playerTwoName").toString())
        binding.playerOneName.text = player1.getPlayerName()
        binding.playerTwoName.text = player2.getPlayerName()

        binding.rollButton.setOnClickListener {
            binding.diceImage.visibility = View.VISIBLE
            animation()
            playerRoll()
        }

        val playerOneTotal = binding.playerOneTscore
        val playerTwoTotal = binding.playerTwoTscore

        binding.holdScore.setOnClickListener {
            //Add current score to active player
            if (activePlayer == 1) {
                player1.addScore(currentScore)
                currentScore = 0
                binding.playerOneCurrent.text = "0"
                playerOneTotal.text = "${player1.getScore()}"
                activePlayer = 2

            } else {
                player2.addScore(currentScore)
                binding.playerTwoCurrent.text = "0"
                currentScore = 0
                playerTwoTotal.text = "${player2.getScore()}"
                activePlayer = 1
            }

            // check  if score =50
            if (player2.getScore() >= 50) {
                playerTwoTotal.text = getString(R.string.win)
                endGame()
            } else if (player1.getScore() >= 50) {
                playerOneTotal.text = getString(R.string.win)
                endGame()
            }
            colorSwap()
            //finish the game
            //switch player
        }

        binding.restart.setOnClickListener { newGame() }

        binding.newGame.setOnClickListener { newGame() }
    }

    private fun playerRoll() {
        val value = dice.roll()
        display(value)

        if (value != 1) {
            currentScore += value
            //going to update current score
            //updating value by checking current player
            activePlayerCheck().text = "$currentScore"
        } else {
            //Switch to Next Player
            currentScore = 0
            activePlayerCheck().text = "$currentScore"
            activePlayer = if (activePlayer == 1) 2
            else 1
            activePlayerCheck().text = "$currentScore"
            colorSwap()
        }
    }

    private fun display(value: Int) {
        dice.showDiceNumber(binding, value)
        Log.d(TAG, "The Dice Rolled $value")
    }

    private fun activePlayerCheck(): TextView {
        return when (activePlayer) {
            1 -> binding.playerOneCurrent
            else -> binding.playerTwoCurrent
        }
    }

    private fun endGame() {
        binding.rollButton.visibility = View.GONE
        binding.newGame.visibility = View.VISIBLE
        binding.holdScore.visibility = View.GONE
        binding.diceImage.visibility = View.GONE
        binding.restart.visibility = View.GONE
    }

    private fun newGame() {
        binding.rollButton.visibility = View.VISIBLE
        binding.newGame.visibility = View.GONE
        binding.holdScore.visibility = View.VISIBLE
        binding.restart.visibility = View.VISIBLE
        binding.diceImage.visibility = View.GONE
        binding.playerOneTscore.text = null
        binding.playerTwoTscore.text = null
        binding.playerOneCurrent.text = null
        binding.playerTwoCurrent.text = null
        currentScore = 0
        player1.resetScore()
        player2.resetScore()
        activePlayer = 1
        colorSwap()
    }

    private fun colorSwap() {
        if (activePlayer == 1) {
            binding.playerOneLayout.setBackgroundResource(R.color.activePlayer)
            binding.playerTwoLayout.setBackgroundResource(R.color.inactvePlayer)
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.color.activePlayer)
            binding.playerOneLayout.setBackgroundResource(R.color.inactvePlayer)
        }
        Log.d(TAG, "color swap method is working")
    }

    private fun animation() {
        binding.diceImage.animate()?.apply {
            rotationY(360f)
        }?.withEndAction {
            binding.diceImage.animate()?.apply {
                rotationY(360f)
            }?.withEndAction {
                binding.diceImage.animate()?.apply {
                    rotationY(0f)
                }
            }
        }
    }

}

class Dice(private val numSides: Int) {
    private val TAG = "Dice"

    //this function returns a random number from 1 to number of sides.In our case number of sides are 6
    fun roll(): Int {
        return (1..numSides).random()
    }

    //this function use while condition to set the image according to the generated random value
    fun showDiceNumber(binding: ActivityMainBinding, value: Int) {
        when (value) {
            1 -> binding.diceImage.setImageResource(R.drawable.dice_1)
            2 -> binding.diceImage.setImageResource(R.drawable.dice_2)
            3 -> binding.diceImage.setImageResource(R.drawable.dice_3)
            4 -> binding.diceImage.setImageResource(R.drawable.dice_4)
            5 -> binding.diceImage.setImageResource(R.drawable.dice_5)
            6 -> binding.diceImage.setImageResource(R.drawable.dice_6)
        }
        Log.d(TAG, "View Display is Working")

    }
}