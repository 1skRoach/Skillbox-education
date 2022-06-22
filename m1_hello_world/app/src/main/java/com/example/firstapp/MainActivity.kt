package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var counter = 50
    var counter2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.plusButton.setOnClickListener {
            binding.minusButton.isEnabled = true
            binding.textView.setTextColor(getColor(R.color.blue))
            binding.textInfo.visibility = View.VISIBLE
            counter--
            counter2++
            binding.textView.text = counter.toString()
            binding.counterTextView.text = counter2.toString()

            if (counter2 >= 50) {
                binding.textView.text = getString(R.string.too_much1)
                binding.textView.setTextColor(getColor(R.color.red))
                binding.plusButton.isEnabled = false
                binding.resetButton.visibility = View.VISIBLE
                binding.textInfo.visibility = View.INVISIBLE
                binding.minusButton.isEnabled = false
            }
        }

        binding.minusButton.setOnClickListener {
            counter2--
            counter++
            binding.textView.text = counter.toString()
            binding.counterTextView.text = counter2.toString()
            if (counter2 == 0) {
                binding.minusButton.isEnabled = false
            }

        }

        binding.resetButton.setOnClickListener {
            binding.textView.text = getString(R.string.all_seats_are_free)
            counter = 50
            counter2 = 0
            binding.textView.text = getText(R.string.all_seats_are_free)
            binding.counterTextView.text = counter2.toString()
            binding.textView.setTextColor(getColor(R.color.green))
            binding.textInfo.visibility = View.INVISIBLE
            binding.plusButton.isEnabled = true
            binding.minusButton.isEnabled = false
            binding.resetButton.visibility = View.INVISIBLE
        }
    }
}