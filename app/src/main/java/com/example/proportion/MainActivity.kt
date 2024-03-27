package com.example.proportion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proportion.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private  val viewModel = ProportionViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var setTextVieww = binding.a1
        var counter = 0

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
        binding.del.setOnClickListener { TODO()
//            setTextVieww.text = viewModel.makerStr("D", counter)
        }
        binding.reset.setOnClickListener { TODO()
//            binding.a1.text = "A"
//            binding.a2.text = "B"
//            binding.b1.text = "C"
//            binding.b2.text = "D"
        }
//        lifecycleScope.launch {
//            var changer = false
//
//            val case1 = binding.a1.text != "A" &&
//                    binding.a2.text != "B" &&
//                    binding.b1.text != "C" &&
//                    binding.b2.text == "D"
//
//            val case2 = binding.a1.text != "A" &&
//                    binding.a2.text != "B" &&
//                    binding.b1.text == "C" &&
//                    binding.b2.text != "D"
//
//            while (changer == false) {
//                if (changer == case1) {}
//
//
//            }
//        }

    }

    fun ChangeComma(str: String): Boolean {

        if (str.contains(".")) {
            return true
        } else return false
    }
}