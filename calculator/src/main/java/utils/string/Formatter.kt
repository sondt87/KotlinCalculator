package utils.string

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


    fun doubleToString( d : Double) : String{
        val symbols = DecimalFormatSymbols(Locale.US)
        symbols.decimalSeparator = '.'
        symbols.groupingSeparator = ','

        val formatter = DecimalFormat()
        formatter.maximumFractionDigits = 12
        formatter.decimalFormatSymbols = symbols
        formatter.isGroupingUsed = true

        return formatter.format(d)
    }

    fun stringToDouble(str : String) : Double{
        return str.replace(",","").toDouble()
    }
