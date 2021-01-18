package com.workbenchstudio.gbkotlinhomework01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            if (login_input.text.toString().isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.hint_if_login_input_empty), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, resources.getString(R.string.hint_on_login), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, login_input.text)
                startActivity(intent)
                finish()
            }
        }
    }


}