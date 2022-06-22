package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.countdowntimer.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var currentProgress = 0
    var isTimerActivated = false
    var startRunning: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scope = CoroutineScope(Dispatchers.Main)

        binding.slider.addOnChangeListener { _, value, _ ->
            binding.counterTextView.text = value.toInt().toString()
            binding.progressBar.progress = value.toInt()

        }

        binding.startButton.setOnClickListener {
            currentProgress = binding.slider.value.toInt()
            if (!isTimerActivated) {
                if (currentProgress > 0) {
                    isTimerActivated = true
                    binding.startButton.setText(R.string.stop)
                    binding.slider.isEnabled = false
                    startRunning = scope.launch {
                        while (currentProgress > 0) {
                            delay(1000)
                            currentProgress--
                            binding.counterTextView.text = currentProgress.toString()
                            binding.progressBar.progress = currentProgress
                        }
                        getStartValues()
                        Toast.makeText(
                            applicationContext,
                            R.string.finish_message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else scope.launch {
                startRunning?.cancel()
                getStartValues()
                Toast.makeText(
                    applicationContext,
                    R.string.stop_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getStartValues() {
        isTimerActivated = false
        binding.startButton.setText(R.string.go)
        binding.slider.isEnabled = true
        binding.counterTextView.text = binding.slider.value.toInt().toString()
        binding.progressBar.progress = binding.slider.value.toInt()
    }
}







