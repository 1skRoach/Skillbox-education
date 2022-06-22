package com.example.homework2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.homework2.databinding.CustomViewActivityBinding

class CustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding = CustomViewActivityBinding.inflate(LayoutInflater.from(context))
    init {
        addView(binding.root)
    }
    fun setUpText(text: String) {
        binding.upTextView.text = text
    }
    fun setDowntext(text: String) {
        binding.downTextView.text = text
    }
}
