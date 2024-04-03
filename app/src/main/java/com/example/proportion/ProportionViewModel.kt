package com.example.proportion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// экземпляр этого класса транслирует состояние поля и его значение. Создаются 4 экземпляра класс,
// каждый экзепляр привязан к своему полю. и он транслирует своему полю его состояние и значение.
// Экземпляры этого класса созданы в классе ProportionViewModel. т.е. реальная трансляция происходит
// из экземпляра класса ProportionViewModel, который создается в Активити.
class FieldViewModel (str: String) {

    private val mutableValueFlow = MutableStateFlow(str)
    val valueFlow: StateFlow<String> = mutableValueFlow.asStateFlow()

    //Пришлось дописать, чтобы запускалось
    //Нужно для того, чтобы подсветку можно было включать
    private val mutableFocusFlow = MutableStateFlow(false)
    val focusFlow: StateFlow<Boolean> = mutableFocusFlow.asStateFlow()

    private val mutableResultFlow = MutableStateFlow(false)
    val resultFlow: StateFlow<Boolean> = mutableResultFlow.asStateFlow()


    private var value = str
    private var start = true

/// возращает тру если поле корректно заполнено
    fun isCorrect(): Boolean {
        if (
            value == "0." ||
            value == "" ||
            value == "A" ||
            value == "B" ||
            value == "C" ||
            value == "D") {
            return false
        } else {
            return true
        }
    }

    suspend fun ddeleteChar() {
        if (value != "") {
            var strCopy = value.replaceFirst(".$".toRegex(), "")
            value = strCopy
        }

        if (value == "0") {value = ""}
        mutableValueFlow.emit(value)
        if (value == "") { start = true}
    }


    suspend fun enterChar (char: Char) {
        if (start) {
            if (char == '.' || char == '0') {value="0."}
            else value=char.toString()
        }
        else {
            if (char == '.' && value.contains(".")) {
                value += ""
            } else { value += char}}

        mutableValueFlow.emit(value)
            //Теперь обрабатываем ввод по новому
        start = false

    }

    suspend fun ssetFocus() {
        //Просто сообщаем view об установке
        mutableFocusFlow.emit(true)
    }

    suspend fun lostFocus() {
        //Просто сообщаем view о потере фокуса
        mutableFocusFlow.emit(false)
    }

    suspend fun ssetResultField() {
        //Просто сообщаем view об установке
        mutableResultFlow.emit(true)
    }

    suspend fun rresetField() {
        value = ""
        start = true
        mutableValueFlow.emit(value)
        mutableResultFlow.emit(false)
    }

    suspend fun setResult(value: Float) {
        mutableValueFlow.emit(value.toString())
    } // передаем результат вычисления.

    fun getValue(): Float {
        return value.toFloat()
    } // возвращает число которое ввел пользователь

    suspend fun ssetValueForResultField(a: Float?) {
        value = a.toString()
        mutableValueFlow.emit(value.toString())
    }


}
// Этот класс управляет экземплярами класса FieldViewModel. обращается через переменную fields к экземпляру
// класса FieldViewModel, т.е. к нужному полю и сообщает что нужно транслировать. в активити есть для
// каждого поля по 2 корутины. всего 8 корутин. первая корутина собирает информацию какое число ей
// записать в это поле, а вторая корурина отслеживает, ативна для записи это поле или нет.
//


class ProportionViewModel: ViewModel() {

    var model = ProportionModel()

    // Вэтой переменной массив экземпляров класса.
    private val fields = arrayOf(
        FieldViewModel("A"),
        FieldViewModel("B"),
        FieldViewModel("C"),
        FieldViewModel("D"),
    )

    fun getFlow(a: Int): StateFlow<String> = fields[a].valueFlow

    // Позволяем получить focusFlow из полей
    fun getFocusFlow(a: Int) = fields[a].focusFlow

    fun getResultFlow(a: Int) = fields[a].resultFlow

    // в этой переменной экземпляр класса FieldViewModel
    private var currentField = fields[0]

    private var resultFieldViewModel:  FieldViewModel? = null


    init {
        //Приходится запускать в coroutine
        viewModelScope.launch {
            currentField.ssetFocus()
        }
    }


    // Если пользователь жмет на поле, оно вызывет эту функцию, передавая имя поля. Создается крутина
    // где сначала вызывается lostFocus() и она сообщает последнему активному полю что оно перестает
    // быть активным, т.е. передает ему (currentField) false. Дальше в currentField записывает экземпляр
    // нового поля. меняется currentField. далее вызывается метод класса FieldViewModel ssetFocus(),
    // который по суть и есть поток (транслятор для текущего поля). поэтому метод в корутине.
    // Этому полю транслируется True, т.е. что оно активно и может отображать транслируемые полю символы
    fun setFocus(a: Int) {
        viewModelScope.launch {
            //Сообщаем о потере фокуса
            currentField.lostFocus()
            //Меняем текущее поле
            currentField = fields[a]
            //Сообщаем о возвращении фокуса
            currentField.ssetFocus()
        }
    }


    // когда пользователь жмет на кнопку с цифрой, вызывается этот метод. метод приннимает тот символ,
    // который надо ввести в активное поле. заводится корутина. в ней вызывается у текщего экземпляра
    // (по сути активного поля) метод enterChar(a) с передаваемом символом в аргументе. enterChar(a)
    // складывеат этот символ с предыдущими и отправляет (испускает) полученную строку в поток для
    // нужного поля. потом активити с помощью метода getFlow(0).collect в определенной корутине получит
    // то, что испускалось через enterChar(a) и потока valueFlow и запишет полученную строку в поле.
    fun enterChar(a: Char) {

        //Запускаем корутину внутри scope viewModelScope для корректного уничтожения
        viewModelScope.launch {
            currentField.enterChar(a)
            resultFieldViewModel?.valueFlow
            checkFields()
            getResultWriteValue()
        }
    }

    fun deleteChar() {
        viewModelScope.launch {
            currentField.ddeleteChar()
            getResultWriteValue()
        }
    }

    fun resetField() {
        for (it in fields) {
            viewModelScope.launch {
                it.rresetField()
            }

        }
    }

    private fun checkFields() {

        val counter = fields.sumOf { if (it.isCorrect()) 1L else 0L }

        if (counter == 3L) {
            val index = fields.indices.first { !fields[it].isCorrect() }

            viewModelScope.launch {
                resultFieldViewModel = fields[index] // Здесь активное поле для вывода результата
                resultFieldViewModel!!.ssetResultField()
                getResultWriteValue()

            }
        }
    }

    fun getResultWriteValue () {
        viewModelScope.launch {
            resultFieldViewModel?.ssetValueForResultField(getResult())
        }

    }

    fun getResult(): Float? {
        when (resultFieldViewModel) {
            fields[0] -> return model.calculate(fields[1].getValue().toFloat(), fields[2].getValue().toFloat(), fields[3].getValue().toFloat())
            fields[1] -> return model.calculate(fields[0].getValue().toFloat(), fields[3].getValue().toFloat(), fields[2].getValue().toFloat())
            fields[2] -> return model.calculate(fields[0].getValue().toFloat(), fields[3].getValue().toFloat(), fields[1].getValue().toFloat())
            fields[3] -> return model.calculate(fields[1].getValue().toFloat(), fields[2].getValue().toFloat(), fields[0].getValue().toFloat())
        }
        return null
    }
}