package com.krp.android.expense.sms

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.krp.android.expense.R

class GenericSMSViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val txtGenericSms = itemView.findViewById<TextView>(R.id.txt_generic_sms)

    init {

    }

    fun onBind(position: Int, sms: GenericSMS) {
        Log.d("MyExpense", "Expense Item drawn position: $position")
        txtGenericSms.text = "${sms.address}\n${sms.body}"
        txtGenericSms.setBackgroundColor(
            if (sms.isCreditDebitSms) Color.GREEN
            else Color.WHITE
        )
    }

}