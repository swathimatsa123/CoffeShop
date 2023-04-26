package com.paypal.coffeeshop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.paypal.coffeeshop.R
import java.text.NumberFormat
import java.util.*
/*import com.paypal.coffeeshop.dataBiniding.ActivityMainBiniding
import com.paypal.coffeeshop.dataBiniding.ActivitySecondBinding*/
/**
 * This app displays an order form to order coffee.
 */
class MainActivity : AppCompatActivity() {
    /**
     * This method is called when the widget is created.
     */
//    val binding: ActivityMainBiniding = TODO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActiviyMainBinding.inflate(layoutInflater, null, false)
        setContentView(R.layout.activity_main)
        // Disable default focus for EditText widget
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        display() // display cups quantity again
    }

    override fun onStart() {
        super.onStart()
        Log.d("Onstart","On start function is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("OnResume","On Resume function is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("OnPause","On Pause function is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("OnStop","On Stop function is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy","On Destroy function is called")
    }

    /**
     * Add whipped cream to the cups of coffee or not
     *
     * @param view - view of the main screen
     */
    fun addFictional(view: View?) {
        hasWhippedCream = !hasWhippedCream
        display()
    }

    /**
     * Add chocolate to the cups of coffee or not
     *
     * @param view - view of the main screen
     */
    fun addNonFictional(view: View?) {
        hasChocolate = !hasChocolate
        display()
    }

    /**
     * Decrement number of cups by 1.
     */
    fun decrement(view: View?) {
        cupsQuantity-- // decrease by 1
        Companion.orderMessage = "" // set empty order message
        display()
    }

    /**
     * Increment number of cups by 1.
     */
    fun increment(view: View?) {
        cupsQuantity++ // increase by 1
        Companion.orderMessage = "" // set empty order message
        display()
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @param view - view of the main screen
     */
    fun submitOrder(view: View?) {
        // Compose e-mail
        val text = orderMessage
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, text[0])
        intent.putExtra(Intent.EXTRA_TEXT, text[1])
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        // Finish the order
        Companion.orderMessage = text[1]
        setZero()
        display()
    }

    private fun setZero() {
        cupsQuantity = 0
        hasWhippedCream = false
        hasChocolate = false
    }// Get user name
    // Get cup(s) word
    // Set text strings
    // set order message
    /**
     * Creates the message output for the order.
     *
     * @return the message
     */
    private val orderMessage: Array<String?>
        private get() {
            val text = arrayOfNulls<String>(2)
            // Get user name
            val name_text = findViewById<EditText>(R.id.name_text)
            var name = name_text.text.toString()
            if (name == "") {
                name = getString(R.string.anonymous)
            }
            // Get cup(s) word
            var cups = " " + getString(R.string.cup_singular) + " "
            if (cupsQuantity > 1) {
                cups = " " + getString(R.string.cups_plural) + " "
            }
            // Set text strings
            text[0] = getString(R.string.coffee_for) + " " + name
            text[1] = """
                ${getString(R.string.name_word, name)}
                ${getString(R.string.quantity_word)}: $cupsQuantity$cups${
                getString(
                    R.string.of_coffee
                )
            }
                ${getString(R.string.whipped_cream_words)}: $hasWhippedCream
                ${getString(R.string.chocolate_word)}: $hasChocolate
                ${getString(R.string.thank_you)}
                """.trimIndent() // set order message
            return text
        }

    /**
     * This method is called when the cancel button is clicked.
     *
     * @param view - view of the main screen
     */
    fun cancelOrder(view: View?) {
        Companion.orderMessage = ""
        setZero()
        display()
    }

    /**
     * This method displays the given cups quantity value on the screen.
     */
    private fun display() {
        val order = findViewById<Button>(R.id.button_borrow) // order button
        val increase = findViewById<Button>(R.id.button_increase) // increase button
        val decrease = findViewById<Button>(R.id.button_decrease) // decrease button
        val cancel = findViewById<Button>(R.id.button_cancel) // cancel button
        if (cupsQuantity <= 0) {  // order only positive cups of coffee
            cupsQuantity = 0 // cups quantity can be >= 0
            order.isEnabled = false // disable button
            decrease.isEnabled = false // disable button
            cancel.isEnabled = false // disable button
            order.setTextColor(
                application.resources.getColor(
                    android.R.color.darker_gray
                )
            ) // change button color
            decrease.setTextColor(
                application.resources.getColor(
                    android.R.color.darker_gray
                )
            ) // change button color
            cancel.setTextColor(
                application.resources.getColor(
                    android.R.color.darker_gray
                )
            ) // change button color
        } else {  // cupsQuantity > 0
            order.isEnabled = true // enable button
            decrease.isEnabled = true // enable button
            cancel.isEnabled = true // enable button
            // Return old button themes
            order.setTextAppearance(applicationContext, R.style.ButtonTheme)
            decrease.setTextAppearance(applicationContext, R.style.ButtonSquare)
            cancel.setTextAppearance(applicationContext, R.style.ButtonTheme)
        }
        if (cupsQuantity >= 10) {
            increase.isEnabled = false // disable button
            increase.setTextColor(
                application.resources.getColor(
                    android.R.color.darker_gray
                )
            ) // change button color
        } else {  // cupsQuantity < 10
            increase.isEnabled = true // enable button
            increase.setTextAppearance(applicationContext, R.style.ButtonSquare)
        }
        // Update quantity number text view
        val quantityTextView = findViewById<TextView>(R.id.text_quantity_number)
        quantityTextView.text =
            String.format(Locale.getDefault(), "%d", cupsQuantity)
        // Display price automatically after quantity of cups is changed
//        val priceTextView = findViewById<TextView>(R.id.text_price_number)
//        totalPrice = calculatePrice(cupsQuantity)
//        priceTextView.text = totalPrice
        // Set order message
        val orderTextView = findViewById<TextView>(R.id.text_order_message)
        orderTextView.text = Companion.orderMessage
        // Set whipped cream or not
        val whippedCream = findViewById<CheckBox>(R.id.fictional_checkbox)
        whippedCream.isChecked = hasWhippedCream
        // Set chocolate or not
        val chocolate = findViewById<CheckBox>(R.id.non_fictional_checkbox)
        chocolate.isChecked = hasChocolate
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private fun calculatePrice(quantity: Int): String {
        var oneCupPrice = COFFEE_PRICE
        if (hasWhippedCream) {
            oneCupPrice += CREAM_PRICE
        }
        if (hasChocolate) {
            oneCupPrice += CHOCOLATE_PRICE
        }
        return NumberFormat.getCurrencyInstance().format(cupsQuantity * oneCupPrice)
    }

    companion object {
        const val COFFEE_PRICE = 5.0 // COFFEE_PRICE of 1 cup of coffee
        const val CREAM_PRICE = 1.0 // CREAM_PRICE of the whipped cream
        const val CHOCOLATE_PRICE = 2.0 // CHOCOLATE_PRICE of the chocolate
        var cupsQuantity = 0 // coffee cups cupsQuantity
        var orderMessage: String? = "" // order message
        var totalPrice = "" // total price for coffee
        var hasWhippedCream = false // add whipped cream or not
        var hasChocolate = false // add chocolate or not
    }
}
