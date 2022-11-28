package com.sadhin.sadhin.dice.roller
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadhin.sadhin.dice.roller.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonStart.setOnClickListener(){
            val secondAct= Intent(this,MainActivity2::class.java)
            secondAct.putExtra("playerOne",binding.editPlayerOne.text.toString())
            secondAct.putExtra("playerTwo",binding.editPlayerTwo.text.toString())
            startActivity(secondAct)
        }
    }
}