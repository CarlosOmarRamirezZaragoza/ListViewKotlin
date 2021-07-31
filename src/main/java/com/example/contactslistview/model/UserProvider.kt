package com.example.contactslistview.model

class UserProvider(var items:ArrayList<String>) {

    constructor():this(arrayListOf<String>())

    fun getUserItems() {
        items.addAll(
            arrayOf(
            "Marco Franco",
            "Raul Alday",
            "Jessica Alba",
            "Roger Waters",
            "Darth Vader",
            "Homer Simpson",
            "Bill Gates",
            "Elon Musk",
            "Enrique Peña",
            "Angeles Rodriguez",
            "Monica Alvarado",
            "Estrella Fugaz",
            "Juana Lopez"
        ))
    }
}