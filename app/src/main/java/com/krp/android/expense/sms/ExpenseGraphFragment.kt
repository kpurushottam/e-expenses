package com.krp.android.expense.sms

import android.content.Context
import android.content.Intent
import android.database.Cursor
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson
import com.krp.android.expense.R
import com.krp.android.expense.util.getAmounts
import com.krp.android.expense.viewmodel.GenericSMSViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExpenseGraphFragment: Fragment() {

    private lateinit var smsViewModel: GenericSMSViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expense_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        smsViewModel = ViewModelProvider(
            requireActivity(),
            requireActivity().defaultViewModelProviderFactory
        )[GenericSMSViewModel::class.java]
        //
        val messages = smsViewModel.messages
        //
        if (messages.isNotEmpty()) {
            val lineData = LineData(LineDataSet(messages.mapIndexed { smsIndex, sms ->
                val entry = Entry(smsIndex.toFloat(), 0F)
                sms.getAmounts().forEachIndexed { amountIndex, amount ->
                    entry.y = amountIndex.toFloat()
                    entry.data = amount.also { finalAmount ->
                        Log.d("ExpenseAmount", finalAmount)
                    }
                }
                entry
            }, "Amount"))

            with(view.findViewById<LineChart>(R.id.container_expenses_graph)) {
                data = lineData
                description = Description().apply {
                    text = "Expenses"
                }
                animateXY(500, 500)
                invalidate()
            }
        }
        //
        with(view.findViewById<TextView>(R.id.txt_item_count)) {
            text = "Count: ${messages.size}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

}