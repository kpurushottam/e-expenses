package com.krp.android.expense.sms

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krp.android.expense.R
import kotlinx.coroutines.launch


class ExpenseMessagesFragment: Fragment() {

    private val messages = arrayListOf <GenericSMS>()
    private val liveMessages = MutableLiveData<List<GenericSMS>?>()

    private var listAdapter: ExpenseMessagesAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewLifecycleOwner.lifecycleScope.launch {
            liveMessages.postValue(reteriveMessagesFromPhone(context))
        }
    }

    /**
     * public static final String INBOX = "content://sms/inbox";
     * public static final String SENT = "content://sms/sent";
     * public static final String DRAFT = "content://sms/draft";
     */
    private fun reteriveMessagesFromPhone(context: Context): List<GenericSMS> {
        val messages = arrayListOf<GenericSMS>()
        val cursor: Cursor? =
            context.contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)

        if (cursor?.moveToFirst() == true) { // must check the result to prevent exception
            do {
                var msgData = ""
                for (idx in 0 until cursor.columnCount) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx)
                }
                messages.add(GenericSMS(message = msgData))
                // use msgData
            } while (cursor.moveToNext())
        } else {
            // empty box, no SMS
        }
        cursor?.close()
        return messages
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

        liveMessages.observe(viewLifecycleOwner) {
            it?.let {
                listAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onDetach() {
        liveMessages.removeObservers(viewLifecycleOwner)
        super.onDetach()
    }

}