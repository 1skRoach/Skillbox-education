package com.example.quiz.ui.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentSurveyBinding
import com.example.quiz.quiz.QuizStorage
import com.example.quiz.ui.customSurveyView.CustomSurveyView
import com.example.quiz.ui.result.ResultFragment
import kotlinx.android.synthetic.main.custom_survey_view.view.*
import kotlinx.android.synthetic.main.fragment_result.*
import java.util.*

class SurveyFragment : Fragment() {
    private val questionList =
        if (Locale.getDefault().country.toString() == "RU") QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        else QuizStorage.getQuiz(QuizStorage.Locale.En)

    private val radioGroupList = mutableListOf<RadioGroup>()

    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setQuestions()

        binding.backButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_dashboard_to_splashActivity,
            )
        }

        val bundle = bundleOf(ResultFragment.QUIZ_ANSWERS_KEY to getFeedback())
        val preResultTextView = ResultFragment

        binding.sendButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_dashboard_to_navigation_notifications, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setQuestions() {
        repeat(questionList.questions.size) {
            val customQuestionView = CustomSurveyView(requireContext()).apply {
                this.question.text = questionList.questions[it].question
                this.answer1.text = questionList.questions[it].answers[0]
                this.answer2.text = questionList.questions[it].answers[1]
                this.answer3.text = questionList.questions[it].answers[2]
                this.answer4.text = questionList.questions[it].answers[3]
            }
            radioGroupList.add(customQuestionView.binding.answerGroup)
            binding.newCustomContainer.addView(customQuestionView)
        }
    }

    private fun getFeedback(): String {
        val answersIndexList = mutableListOf<Int>()
        for (i in questionList.questions.indices) {
            answersIndexList.add(
                when (radioGroupList[i].checkedRadioButtonId) {
                    R.id.answer1 -> 0
                    R.id.answer2 -> 1
                    R.id.answer3 -> 2
                    R.id.answer4 -> 3
                    else -> 0
                }
            )
        }
        return QuizStorage.answer(questionList, answersIndexList)
    }
}

