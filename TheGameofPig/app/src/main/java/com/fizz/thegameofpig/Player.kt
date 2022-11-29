package com.fizz.thegameofpig

import android.util.Log

class Player(private var playerName:String){
    private val TAG = "PLAYER"
    private var name = this.playerName
    private var totalScore = 0

    fun addScore(points:Int){
        this.totalScore += points
        Log.d(TAG, "After adding, Player score: "+ this.totalScore)
    }
    fun resetScore(){
        this.totalScore = 0
        Log.d(TAG, "After resetting, Player score: "+this.totalScore)
    }
    fun getScore():Int{
        return this.totalScore
    }
    fun getPlayerName():String{
        return this.name
    }
}