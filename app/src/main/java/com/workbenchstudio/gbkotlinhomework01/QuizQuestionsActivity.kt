package com.workbenchstudio.gbkotlinhomework01

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.workbenchstudio.gbkotlinhomework01.Constants.ANSWER_NOT_CHOOSE
import com.workbenchstudio.gbkotlinhomework01.entity.Question
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private lateinit var mQuestionsList: ArrayList<Question>
    private var mSelectedOptionPosition: Int = ANSWER_NOT_CHOOSE
    private var mCorrectAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        button_option_one.setOnClickListener(this)
        button_option_two.setOnClickListener(this)
        button_option_three.setOnClickListener(this)
        button_option_four.setOnClickListener(this)
        submit_button.setOnClickListener(this)
    }

    private fun setQuestion() {

        mSelectedOptionPosition = ANSWER_NOT_CHOOSE
        val question: Question = mQuestionsList[mCurrentPosition - 1]

        defaultOptionsView()


        submit_button.text = resources.getString(R.string.submit_button_text)


        progressBar.progress = mCurrentPosition
        progressBar.max = mQuestionsList.size
        tv_progress.text = "$mCurrentPosition" + " / " + mQuestionsList.size

        tv_question.text = question!!.question
        button_option_one.text = question.optionOne
        button_option_two.text = question.optionTwo
        button_option_three.text = question.optionThree
        button_option_four.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<Button>()
        options.add(0, button_option_one)
        options.add(1, button_option_two)
        options.add(2, button_option_three)
        options.add(3, button_option_four)

        for (option: Button in options) {
            option.setTextColor(Color.BLACK)
            option.setBackgroundColor(Color.LTGRAY)
            option.isClickable = true
        }
    }

    private fun disableOptionsView() {
        val options = ArrayList<Button>()
        options.add(0, button_option_one)
        options.add(1, button_option_two)
        options.add(2, button_option_three)
        options.add(3, button_option_four)

        for (option: Button in options) {
            option.isClickable = false
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_option_one -> {
                selectedOptionView(button_option_one, 1)
            }
            R.id.button_option_two -> {
                selectedOptionView(button_option_two, 2)
            }
            R.id.button_option_three -> {
                selectedOptionView(button_option_three, 3)
            }
            R.id.button_option_four -> {
                selectedOptionView(button_option_four, 4)
            }
            R.id.submit_button -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList.size -> {
                            setQuestion()
                        }
                        else -> {
                            val userName = intent.getStringExtra(Constants.USER_NAME)
                            val intent = Intent(this, ResultActivivy::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else if (mSelectedOptionPosition == ANSWER_NOT_CHOOSE) {
                    Toast.makeText(this, resources.getString(R.string.choose_answer_hint), Toast.LENGTH_SHORT).show()
                } else {
                    val question = mQuestionsList[mCurrentPosition - 1]
                    if (question.correctAnswer != mSelectedOptionPosition) {
                        setColorOnAnswer(mSelectedOptionPosition, Color.RED)
                    } else {
                        mCorrectAnswers++
                    }
                    setColorOnAnswer(question.correctAnswer, Color.GREEN)

                    if (mCurrentPosition == mQuestionsList.size) {
                        submit_button.text = resources.getString(R.string.finish_button_text)
                    } else {
                        submit_button.text = resources.getString(R.string.next_question_text)
                        disableOptionsView()
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun setColorOnAnswer(answer: Int, color: Int) {
        when (answer) {
            1 -> {
                button_option_one.setBackgroundColor(color)
            }
            2 -> {
                button_option_two.setBackgroundColor(color)
            }
            3 -> {
                button_option_three.setBackgroundColor(color)
            }
            4 -> {
                button_option_four.setBackgroundColor(color)
            }
        }
    }

    private fun selectedOptionView(tv: Button, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.BLUE)
    }
}