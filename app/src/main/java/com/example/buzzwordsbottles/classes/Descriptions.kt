package com.example.buzzwordsbottles.classes

class Descriptions(
    val description: String,
    var title: String,
    val score: Float,

    private val descriptionLength: Int = 30
){

    init {
        title = assignName()
    }

    private fun assignName(): String{
        var nameReturn = ""

        if (description.length < descriptionLength){
            return description
        }

        for (i in 0..descriptionLength){
            nameReturn += description[i]
        }


        return "$nameReturn..."
    }
}
