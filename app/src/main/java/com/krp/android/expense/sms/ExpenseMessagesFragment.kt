package com.krp.android.expense.sms

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.krp.android.expense.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExpenseMessagesFragment: Fragment() {

    private val messages = arrayListOf <GenericSMS>()
    private val liveMessages = MutableLiveData<GenericSMS>()

    private var listAdapter: ExpenseMessagesAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycleScope.launch(Dispatchers.IO) {
            reteriveMessagesFromPhone(context)
        }
    }

    /**
     * public static final String INBOX = "content://sms/inbox";
     * public static final String SENT = "content://sms/sent";
     * public static final String DRAFT = "content://sms/draft";
     */
    private fun reteriveMessagesFromPhone(context: Context) {
        val messages = arrayListOf<GenericSMS>()
        val cursor: Cursor? =
            context.contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)

        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                val map = hashMapOf<String, String?>()
                for (idx in 0 until cursor.columnCount) {
                    map[cursor.getColumnName(idx)] = cursor.getString(idx)
                }
                val sms = Gson().fromJson(Gson().toJson(map), GenericSMS::class.java)
                if (sms.isCreditDebitSms()) {
                    messages.add(sms)
                    liveMessages.postValue(sms)
                    Log.d("MyExpense", "Posted sms ${sms.id}\n${sms.body}\n\n")
                }
                // use msgData
            } while (cursor.moveToNext())
        } else {
            // empty box, no SMS
        }
        cursor?.close()
        messages.also {
            Log.d("MyExpense", "Expense List is ${it.size}")
        }
    }

    private fun GenericSMS.isCreditDebitSms(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expense_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(view.findViewById<RecyclerView>(R.id.container_expenses_sms)) {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ExpenseMessagesAdapter(messages).also {
                listAdapter = it
            }
        }

        liveMessages.observe(viewLifecycleOwner) { sms ->
            val currentCount = messages.size
            messages.add(sms)
            listAdapter?.notifyItemRangeInserted(currentCount, (messages.size - currentCount))
            //
            with(view.findViewById<TextView>(R.id.txt_item_count)) {
                text = "Count: ${messages.size}"
            }
        }
    }

    override fun onDestroyView() {
        liveMessages.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

}