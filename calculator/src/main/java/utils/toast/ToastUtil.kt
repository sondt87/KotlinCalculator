package utils.toast

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, resId: Int) {
    Toast.makeText(context, context.getText(resId), Toast.LENGTH_SHORT).show()
}