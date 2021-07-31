package com.example.contactslistview.viewmodel

import androidx.lifecycle.ViewModel
import com.example.contactslistview.model.UserProvider

class UserViewModel: ViewModel() {
    val userProvider = UserProvider()

    fun returnUser():UserProvider{
        userProvider.getUserItems()
        return userProvider
    }
}