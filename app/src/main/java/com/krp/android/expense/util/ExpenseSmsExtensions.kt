package com.krp.android.expense.util

import com.krp.android.expense.sms.GenericSMS

fun GenericSMS.isCreditDebitSms(): Boolean {
    val regex = Regex("((INR\\s*\\d+(\\.|\\,){0,1}\\d{1,2})|(Rs(\\.){0,1}\\s*\\d+(\\.|\\,){0,1}\\d{1,2}))")
    (body?.contains(regex) == true).also {
        isCreditDebitSms = it
    }
    return true
}

fun GenericSMS.getAmounts(): List<String> {
    val regex = Regex("((INR\\s*\\d+(\\.|\\,){0,1}\\d{1,2})|(Rs(\\.){0,1}\\s*\\d+(\\.|\\,){0,1}\\d{1,2}))")
    val input = body ?: ""
    val match = regex.findAll(input)
    return match.map { it.value }.toList()
}