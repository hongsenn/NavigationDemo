package com.mad.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.mad.navigationdemo.databinding.FragmentLoginBinding
import com.mad.navigationdemo.databinding.FragmentQuestion2Binding


class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestion2Binding
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    var questionIndex = 0
    var score: Int = 0

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "what is mco stand for ?",
            answers = listOf(
                "Movement Control Order",
                "Multiple Choice Order",
                "More Coffee Order "
            )
        ),
        Question(
            text = "What is FMCO?",
            answers = listOf(
                "Full Movement Control Oder",
                "Fun Movement Control Oder",
                "Forever Movement Control Oder"
            )
        )
    )

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()

        answers.shuffle()

        binding.tvQuestion.text = currentQuestion.text
        binding.optionA.text = answers[0]
        binding.optionB.text = answers[1]
        binding.optionC.text = answers[2]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question2, container, false)

        setQuestion()

        binding.buttonNext.setOnClickListener(){
            val checkedId = binding.radioGroup.checkedRadioButtonId

            if (checkedId != -1) {
                var answerIndex = 0

                when (checkedId) {
                    R.id.optionA -> answerIndex = 0
                    R.id.optionB -> answerIndex = 1
                    R.id.optionC -> answerIndex = 2
                }
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    score += 1
                }

                if (questionIndex < 1) {
                    questionIndex += 1
                    binding.radioGroup.clearCheck() //clear the selection
                    setQuestion()

                } else {
                    Navigation.findNavController(it).navigate(R.id.action_questionFragment_to_thankyouFragment)
                }
            }else{
                Toast.makeText(context, "please select the answer", Toast.LENGTH_LONG).show()
            }
        }



        return binding.root
    }

}