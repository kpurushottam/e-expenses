package com.krp.android.expense

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.krp.android.expense.sms.ExpenseMessagesFragment
import com.permissionx.guolindev.PermissionX

/**
 * Permission Library
 * https://github.com/guolindev/PermissionX
 *
 * PieChar Library
 * https://github.com/PhilJay/MPAndroidChart
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btn_read_sms).setOnClickListener {
            PermissionX.init(this)
                .permissions(Manifest.permission.READ_SMS)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                        loadExpenseMessages()
                    } else {
                        Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
        }

        findViewById<Button>(R.id.btn_draw_graph).setOnClickListener {
            loadExpenseGraph()
        }
    }

    private fun loadExpenseMessages() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_content, ExpenseMessagesFragment(), ExpenseMessagesFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

    private fun loadExpenseGraph() {

    }
}