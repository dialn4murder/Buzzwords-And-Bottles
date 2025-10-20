package com.example.buzzwordsbottles.classes

class Descriptions(
    val description: String,
    var title: String,
    val score: Float
){

    init {
        title = assignName()
    }

    private fun assignName(): String{
        var nameReturn = ""

        if (description.length < 6){
            return description
        }

        for (i in 0..5){
            nameReturn += description[i]
        }


        return "$nameReturn..."
    }
}
