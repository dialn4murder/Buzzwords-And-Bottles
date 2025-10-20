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

        if (description.length < 15){
            return description
        }

        for (i in 0..14){
            nameReturn += description[i]
        }


        return "$nameReturn..."
    }
}
