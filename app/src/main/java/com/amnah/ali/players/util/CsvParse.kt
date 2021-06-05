package com.amnah.ali.players.util

import com.amnah.ali.players.data.Players

class CsvParse {

    //split values in csv file to be as a table
    fun parse(line : String) : Players {
        val tokens = line.split(",")
        return Players(
            name = tokens[Constants.ColumnIndex.NAME],
            details = tokens[Constants.ColumnIndex.DETAILS],
            imgProfile = tokens[Constants.ColumnIndex.IMG_PROFILE],
            )
    }

}