package com.sadhin.sadhin.dice.roller
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sadhin.sadhin.dice.roller.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private var firstPlayer=0
    private var secondPlayer=0
    private var temp=0
    private var playerSelect=true
    private var playerOneName:String?=""
    private var playerTwoName:String?=""
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        playerOneName =intent.getStringExtra("playerOne")
        playerTwoName =intent.getStringExtra("playerTwo")
        display()
        binding.buttonRoll.setOnClickListener { playerRoll() }
        binding.buttonSwitch.setOnClickListener {
            if(playerSelect){
                firstPlayer+=temp
            }
            else{ secondPlayer+=temp }
            playerSelect=!playerSelect
            temp=0
            display()
            check()
            Log.d("#####################","first player ${firstPlayer} and second player ${secondPlayer}")
        }
    }
    private fun playerRoll(){
        val value = rollDice()
        if (value == 1) {
            playerSelect = !playerSelect
            temp=0
            binding.textCurrentValue.text = getString(R.string.turn)
        } else { temp = temp + value }
        display()
    }
    private fun rollDice():Int {
        val dice = Dice(6)
        val result = dice.roll()
        val drawableResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.imageDice.setImageResource(drawableResource)
        binding.textDice.text = "$result"
        return result
    }
    private fun display(){
        binding.textDisplay.text = ""
        binding.textCurrentValue.text = getString(R.string.current)+"\n${temp.toString()}"
        binding.textPlayerOne.text = playerOneName+"\n${firstPlayer.toString()}"
        binding.textPlayerTwo.text = playerTwoName+"\n ${secondPlayer.toString()}"
        if (playerSelect){
            binding.buttonRoll.text=getString(R.string.player_01)
            binding.buttonRoll.setBackgroundColor(Color.RED)
        }
        else{
            binding.buttonRoll.text=getString(R.string.player_02)
            binding.buttonRoll.setBackgroundColor(Color.BLUE)
        }
    }
    private fun check(){
        if (firstPlayer>=50){
            binding.textDisplay.text = playerOneName+getString(R.string.win)
            reset()
        }
        else if(secondPlayer>=50){
            binding.textDisplay.text = playerTwoName+getString(R.string.win)
            reset()
        }
    }
    private fun reset(){
        firstPlayer=0
        secondPlayer=0
        playerSelect=true
        temp=0
    }
}
