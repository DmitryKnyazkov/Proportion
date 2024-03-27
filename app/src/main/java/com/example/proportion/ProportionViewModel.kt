package com.example.proportion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FieldViewModel (str: String) {
    private val mutableValueFlow = MutableStateFlow(str)

    val valueFlow: StateFlow<String> = mutableValueFlow.asStateFlow()

    //Пришлось дописать, чтобы запускалось
    //Нужно для того, чтобы подсветку можно было включать
    private val mutableFocusFlow = MutableStateFlow(false)
    val focusFlow: StateFlow<Boolean> = mutableFocusFlow.asStateFlow()
    private var value = str
    private var start = true

/// возращает тру если поле корректно заполнено
    fun isCorrect(): Boolean {TODO()}

    suspend fun enterChar (char: Char) {
        if (start) {value=char.toString()}
        else value += char
        mutableValueFlow.emit(value)
        //Теперь обрабатываем ввод по новому
        start = false
    }

    suspend fun setFocus() {
        //Просто сообщаем view об установке
        mutableFocusFlow.emit(true)
    }

    suspend fun lostFocus() {
        //Просто сообщаем view о потере фокуса
        mutableFocusFlow.emit(false)
    }

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

    // Позволяем получить focusFlow из полей
    fun getFocusFlow(a: Int) = fields[a].focusFlow


    private var currentField = fields[0]

    init {
        //Приходится запускать в coroutine
        viewModelScope.launch {
            currentField.setFocus()
        }
    }

    fun setFocus(a: Int) {
        viewModelScope.launch {
            //Сообщаем о потере фокуса
            currentField.lostFocus()
            //Меняем текущее поле
            currentField = fields[a]
            //Сообщаем о возвращении фокуса
            currentField.setFocus()
        }
    }

    fun enterChar(a: Char) {
        //Запускаем корутину внутри scope viewModelScope для корректного уничтожения
        viewModelScope.launch {
            currentField.enterChar(a)
        }
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