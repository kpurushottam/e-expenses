package com.krp.android.expense.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.krp.android.expense.sms.GenericSMS

class GenericSMSViewModel(private val application: Application)
    : AndroidViewModel(application) {

    val messages by lazy { arrayListOf<GenericSMS>() }

}