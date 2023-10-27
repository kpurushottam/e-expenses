package com.krp.android.expense.sms

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
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
        val regex = Regex("((INR\\s*\\d+(\\.|\\,){0,1}\\d{1,2})|(Rs(\\.){0,1}\\s*\\d+(\\.|\\,){0,1}\\d{1,2}))")
        (body?.contains(regex) == true).also {
            isCreditDebitSms = it
        }
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
        with(view.findViewById<Button>(R.id.btn_btn1)) {
            val shortcut = ShortcutInfoCompat.Builder(context, "${System.currentTimeMillis()}")
                .setShortLabel("Website")
                .setLongLabel("Open the website")
//                .setIcon(IconCompat.createWithResource(context, R.drawable.icon_shortcuts_30fps))
                .setIcon(IconCompat.createWithBitmap(textAsBitmap("1", 15f, Color.GREEN)!!))
                .setIntent(
                    Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/"))
                )
                .build()

            ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
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

    fun textAsBitmap(text: String?, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.5f).toInt() // round
        val height = (baseline + paint.descent() + 0.5f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text!!, 0f, baseline, paint)
        return image
    }

    override fun onDestroyView() {
        liveMessages.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

}