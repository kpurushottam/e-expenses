package com.krp.android.expense.sms;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krp.android.expense.R

class ExpenseMessagesAdapter(private val messages: List<GenericSMS>) :
    RecyclerView.Adapter<GenericSMSViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericSMSViewHolder {
        return GenericSMSViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_generic_sms, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: GenericSMSViewHolder, position: Int) {
        holder.onBind(position, messages[position])
    }

}
