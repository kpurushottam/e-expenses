package com.krp.android.expense

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ShortcutReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Added to Home Screen", Toast.LENGTH_LONG).show()
        Log.d("Shortcuts", "Added")
    }
}