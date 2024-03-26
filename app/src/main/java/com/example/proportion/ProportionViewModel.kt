package com.example.proportion

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.textfield.TextInputLayout.LengthCounter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProportionViewModel: ViewModel() {
    private  val mutableStateFlow = MutableStateFlow<String?>(null)
    val result = mutableStateFlow.asStateFlow()
    var str = ""
    var counter = 0

    fun makerStr(char: String, counterr: Int): String {

        if (counter == counterr) {
            if (char == "D") {
                var strCopy = str.replaceFirst(".$".toRegex(), "")
                str = strCopy
                return str
            } else {
                str = str + char
                return str}
        } else {
            counter = counterr
            str = ""
            str = str + char
            return str
        }
    }

}