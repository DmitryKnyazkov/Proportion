package com.example.proportion

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.proportion.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    //Здесь нельзя было просто вызывать конструктор viewModel (это неверно работает при
    //поворотах экрана и других изменениях конфигурации)
    private val viewModel: ProportionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //  var setTextVieww = binding.a1
        //  var counter = 0

        //TODO  Подсказка - дублирование можно убрать, если
        // binding.a1, binding.a2, binding.b1, binding.b2 записать в масив
        binding.a1.setOnClickListener {
//            setTextVieww = binding.a1
//            counter++
            viewModel.setFocus(0)

        }
        binding.a2.setOnClickListener {
//            setTextVieww = binding.a2
//            counter++
            viewModel.setFocus(1)
        }
        binding.b1.setOnClickListener {
//            setTextVieww = binding.b1
//            counter++
            viewModel.setFocus(2)
        }
        binding.b2.setOnClickListener {
//            setTextVieww = binding.b2
//            counter++
            viewModel.setFocus(3)
        }

        binding.one1.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("1", counter)
            viewModel.enterChar('1')
        }
        binding.two2.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("2", counter)
            viewModel.enterChar('2')
        }
        binding.three3.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("3", counter)
            viewModel.enterChar('3')
        }
        binding.four4.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("4", counter)
            viewModel.enterChar('4')
        }
        binding.five5.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("5", counter)
            viewModel.enterChar('5')
        }
        binding.six6.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("6", counter)
            viewModel.enterChar('6')
        }
        binding.seven7.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("7", counter)
            viewModel.enterChar('7')
        }
        binding.eight8.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("8", counter)
            viewModel.enterChar('8')
        }

        binding.nine9.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("9", counter)
            viewModel.enterChar('9')
        }
        binding.zero0.setOnClickListener {
//            setTextVieww.text = viewModel.makerStr("0", counter)
            viewModel.enterChar('0')
        }
        binding.comma.setOnClickListener {
//            val str = setTextVieww.text.toString()
//            if (ChangeComma(str)) { }
//            else setTextVieww.text = viewModel.makerStr(".", counter)
            viewModel.enterChar('.')
        }

        //Только отправить во ViewModel
        binding.del.setOnClickListener {
            viewModel.deleteChar()
//            setTextVieww.text = viewModel.makerStr("D", counter)
        }

        //Только отправить во ViewModel
        binding.reset.setOnClickListener {
            viewModel.resetField()
        }

        lifecycleScope.launch {

            //Запускаем при появлении на экране, останавливаем при исчезании
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Далее 4 корутины запускаем параллельно.
                //Иначе строки после collect никогда не будут работать
                //TODO  Подсказка - дублирование можно убрать, если
                // binding.a1, binding.a2, binding.b1, binding.b2 записать в масив
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getFlow(0).collect {
                        binding.a1.text = it
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего второму field
                    viewModel.getFlow(1).collect {
                        binding.a2.text = it
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего третьему field
                    viewModel.getFlow(2).collect {
                        binding.b1.text = it
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего четвертому field
                    viewModel.getFlow(3).collect {
                        binding.b2.text = it
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getFocusFlow(0).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.a1.background = ColorDrawable(
                            if (it) Color.WHITE else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getFocusFlow(1).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.a2.background = ColorDrawable(
                            if (it) Color.WHITE else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getFocusFlow(2).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.b1.background = ColorDrawable(
                            if (it) Color.WHITE else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getFocusFlow(3).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.b2.background = ColorDrawable(
                            if (it) Color.WHITE else Color.GRAY
                        )
                    }
                }





                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getResultFlow(0).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.a1.background = ColorDrawable(
                            if (it) Color.GREEN  else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getResultFlow(1).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.a2.background = ColorDrawable(
                            if (it) Color.GREEN  else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getResultFlow(2).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.b1.background = ColorDrawable(
                            if (it) Color.GREEN  else Color.GRAY
                        )
                    }
                }
                launch {
                    //Выводим строки из flow, соответствующего первому field
                    viewModel.getResultFlow(3).collect {
                        //Если выделено то делаем белый цвет, иначе серый
                        binding.b2.background = ColorDrawable(
                            if (it) Color.GREEN else Color.GRAY
                        )
                    }
                }
            }

        }

    }
}