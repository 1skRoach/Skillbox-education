package com.example.mvvm_homework_12.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_homework_12.R
import com.example.mvvm_homework_12.State
import com.example.mvvm_homework_12.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searcButton.setOnClickListener {
            viewModel.onSearchClick()
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Started -> {
                                controlAmountOfCharacters()
                            }
                            State.Loading -> {
                                loadProcess()
                            }
                            State.Success -> {
                                succeedProcess()
                            }
                        }
                    }

            }
    }

    private fun controlAmountOfCharacters() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                binding.searcButton.isEnabled = binding.searchView.length() >= 3
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun loadProcess() {
        binding.progressBar.isVisible = true
        binding.searcButton.isEnabled = false
    }

    private fun succeedProcess() {
        val resultMessage = getString(R.string.result_info)
        val editSearchText = binding.searchView.text.toString()
        binding.searcButton.isEnabled = true
        binding.progressBar.isVisible = false
        binding.messageTextView.text = "$resultMessage <$editSearchText>"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}