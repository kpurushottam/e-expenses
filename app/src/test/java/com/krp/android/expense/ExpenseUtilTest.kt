package com.krp.android.expense

import com.krp.android.expense.util.ExpenseSmsUtility
import com.krp.android.expense.util.ExpenseSmsUtility.parseAmount
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExpenseUtilTest {

    /**
     * 2,74.00 -> 0.0
     * 1,49.00 -> 0.0
     * 18,12.00 -> 0.0
     * 2,60.00 -> 0.0
     * 27,00.00 -> 0.0
     */
    @Test
    fun parse_test() {
        parse("Rs24", "24.0", "Input Rs24")
        parse("Rs24.00", "24.0", "Input Rs24.00")
        parse("Rs 24", "24.0", "Input Rs 24")
        parse("Rs 24.00", "24.0", "Input Rs 24.00")
        parse("Rs.24", "24.0", "Input Rs.24")
        parse("Rs.24.00", "24.0", "Input Rs.24.00")
        parse("Rs. 24", "24.0", "Input Rs. 24")
        parse("Rs. 24.00", "24.0", "Input Rs. 24.00")
        parse("INR24", "24.0", "Input INR24")
        parse("INR24.00", "24.0", "Input INR24.00")
        parse("INR 24", "24.0", "Input INR 24")
        parse("INR 24.00", "24.0", "Input INR 24.00")
    }

    private fun parse(amount: String, expected: String, msg: String) {
        assertEquals(msg, expected, amount.parseAmount().toString())
    }
}