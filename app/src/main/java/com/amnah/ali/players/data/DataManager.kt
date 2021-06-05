package com.amnah.ali.players.data

object DataManager {
    val playerList: MutableList<Players> = mutableListOf()
    //Add items to list
    fun addPlayer(players: Players){
        playerList.add(players)
    }


}