package com.example.proportion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proportion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private  val viewModel = ProportionViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var setTextVieww = binding.a1
        var counter = 0

        binding.a1.setOnClickListener {
            setTextVieww = binding.a1
            counter++
        }
        binding.a2.setOnClickListener {
            setTextVieww = binding.a2
            counter++
        }
        binding.b1.setOnClickListener {
            setTextVieww = binding.b1
            counter++
        }
        binding.b2.setOnClickListener {
            setTextVieww = binding.b2
            counter++
        }
        binding.one1.setOnClickListener { setTextVieww.text = viewModel.makerStr("1", counter)  }
        binding.two2.setOnClickListener { setTextVieww.text = viewModel.makerStr("2", counter) }
        binding.three3.setOnClickListener { setTextVieww.text = viewModel.makerStr("3", counter) }
        binding.four4.setOnClickListener { setTextVieww.text = viewModel.makerStr("4", counter) }
        binding.five5.setOnClickListener { setTextVieww.text = viewModel.makerStr("5", counter) }
        binding.six6.setOnClickListener { setTextVieww.text = viewModel.makerStr("6", counter) }
        binding.seven7.setOnClickListener { setTextVieww.text = viewModel.makerStr("7", counter) }
        binding.eight8.setOnClickListener { setTextVieww.text = viewModel.makerStr("8", counter) }
        binding.nine9.setOnClickListener { setTextVieww.text = viewModel.makerStr("9", counter) }
        binding.zero0.setOnClickListener { setTextVieww.text = viewModel.makerStr("0", counter) }
        binding.comma.setOnClickListener {
            val str = setTextVieww.text.toString()
            if (ChangeComma(str)) { }
            else setTextVieww.text = viewModel.makerStr(".", counter)
        }
        binding.del.setOnClickListener { setTextVieww.text = viewModel.makerStr("D", counter) }
        binding.reset.setOnClickListener {
            binding.a1.text = ""
            binding.a2.text = ""
            binding.b1.text = ""
            binding.b2.text = ""
        }

    }

    fun ChangeComma(str: String): Boolean {

        if (str.contains(".")) {
            return true
        } else return false
    }
}