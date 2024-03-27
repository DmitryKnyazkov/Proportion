package com.example.proportion

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.textfield.TextInputLayout.LengthCounter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class FieldViewModel (str: String) {
    private val mutableValueFlow = MutableStateFlow(str)

    val valueFlow: StateFlow<String> = mutableValueFlow.asStateFlow()

    val focusFlow: StateFlow<Boolean> = TODO()
    private var value = str
    private var start = true

/// возращает тру если поле корректно заполнено
    fun isCorrect(): Boolean {TODO()}

    suspend fun enterChar (char: Char) {
        if (start) {value=char.toString()}
        else value += char
        mutableValueFlow.emit(value)
    }

    fun setFocus () {TODO()}

    fun  lostFocus () {TODO()}

    fun setResult(value: Float) {TODO()} // передаем результат вычисления.

    fun getValue(): Float {TODO()} // возвращает число которое ввел пользователь


}


class ProportionViewModel: ViewModel() {
//    private  val mutableStateFlow = MutableStateFlow<String?>(null)
//    val result = mutableStateFlow.asStateFlow()
//    var str = ""
//    var counter = 0

    private val fields = arrayOf(
        FieldViewModel("A"),
        FieldViewModel("B"),
        FieldViewModel("C"),
        FieldViewModel("D"),
        )

    fun getFlow(a: Int): StateFlow<String> = fields[a].valueFlow

    private val currentField = fields[0]
    init {currentField.setFocus()}

    fun setFocus(a: Int) {TODO()}

    fun enterChar(a: Char) {
        currentField.enterChar(a)
    }

    private fun ChangeField() {

        val counter = fields.sumOf { if (it.isCorrect()) 1L else 0L }
//        var counter = 0
//        for (field in fields) {
//            if (field.isCorrect()) {
//                counter++
//            }
//        }
        if (counter == 3L) {
            val index = fields.indices.first{ !fields[it].isCorrect() }
            TODO()
        }
    }

//    fun makerStr(char: String, counterr: Int): String {
//
//        if (counter == counterr) {
//            if (char == "D") {
//                var strCopy = str.replaceFirst(".$".toRegex(), "")
//                str = strCopy
//                return str
//            } else {
//                str = str + char
//                return str}
//        } else {
//            counter = counterr
//            str = ""
//            str = str + char
//            return str
//        }
//    }

}