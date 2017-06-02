package utils

import android.content.Context
import android.content.SharedPreferences

class Config (context: Context){
    var prefs: SharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)

    fun getIsFirstRun(key : String) : Boolean{
        return prefs.getBoolean(key, true)
    }
}