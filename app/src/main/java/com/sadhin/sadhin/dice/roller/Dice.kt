package com.sadhin.sadhin.dice.roller

class Dice(private val numOfSides:Int) {

    fun roll(): Int {
        if(numOfSides<1){ return 0 }
        return (1..numOfSides).random()
    }
    fun oddEven(): Boolean {
        val dice = roll()
        return dice % 2 == 0
    }
    fun twoRollEqual():Boolean{
       return roll()==roll()
    }
    fun rollValidation():Int?{
        val dice=roll()
        if (dice==0){return null}
        return dice
    }
}