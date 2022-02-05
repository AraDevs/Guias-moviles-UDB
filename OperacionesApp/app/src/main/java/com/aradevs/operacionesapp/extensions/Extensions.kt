package com.aradevs.operacionesapp.extensions

import android.widget.EditText

/**
 * parseValueToDouble()
 *
 * Converts and Editable to a Double value
 */
fun EditText.parseValueToDouble(): Double {
    return this.text.toString().toDouble()
}