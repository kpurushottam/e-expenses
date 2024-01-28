package com.krp.android.expense.util

import android.util.Log
import com.krp.android.expense.sms.GenericSMS
import java.text.NumberFormat

object ExpenseSmsUtility {

    /**
     * Rs 35,00
     * Rs.580
     * Rs. 52852.00
     */
    fun String.parseAmount(): Float {
        System.out.println("Parsing started")
        var returnValue = 0f;
        try {
            Log.d("ExpenseAmountParsed", "Before Parsed: $this")
            System.out.println("Before Parsed: $this")
            var postRegex = replace("(Rs(\\.){0,1}\\s*|INR\\s*)".toRegex(), "")
            if (postRegex.contains(".").not()) {
                postRegex = "$postRegex.00"
            }
            Log.d("ExpenseAmountParsed", "Post Regex: $postRegex")
            System.out.println("Post Regex: $postRegex")
            returnValue = postRegex.toFloat()
            Log.d("ExpenseAmountParsed", "After Parsed: $returnValue")
            System.out.println("After Parsed: $returnValue")
        } catch (e: Exception) {}
        return returnValue
    }

}