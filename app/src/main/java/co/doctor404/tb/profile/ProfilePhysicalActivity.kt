package co.doctor404.tb.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import co.doctor404.tb.R
import kotlinx.android.synthetic.main.activity_profile_physical.*
import kotlin.math.round

class ProfilePhysicalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_physical)

        val sp = getSharedPreferences("physical", MODE_PRIVATE)

        height.addTextChangedListener(object : TextWatcher {
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                try {
                    val wt = weight.text.toString().toDouble()
                    val ht = height.text.toString().toDouble()
                    bmi.text = String.format("%.2f", wt / (ht * ht))
                } catch (ex: Exception) {
                    bmi.text = "Enter above details"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        weight.addTextChangedListener(object : TextWatcher {
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                try {
                    val wt = weight.text.toString().toDouble()
                    val ht = height.text.toString().toDouble()
                    bmi.text = String.format("%.2f", wt / (ht * ht))
                } catch (ex: Exception) {
                    bmi.text = "Enter above details"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        bmi.addTextChangedListener(object : TextWatcher {
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                if (bmi.text.equals("Enter above details")) {
                    bmi.setTextColor(Color.BLACK)
                } else {
                    var bval: Double = -1.0
                    try {
                        bval = bmi.text.toString().toDouble()
                    } catch (e: Exception) {
                        bval = -1.0
                    }
                    if (bval < 0) {
                        bmi.setTextColor(Color.BLACK)
                        remarks.text = ""
                    } else if (bval < 18.5) {
                        bmi.setTextColor(Color.GRAY)
                        remarks.text = "( Malnutrition )"
                    } else if (bval < 24.9) {
                        bmi.setTextColor(Color.GREEN)
                        remarks.text = "( Normal )"
                    } else if (bval < 29.9) {
                        bmi.setTextColor(Color.RED)
                        remarks.text = "( Overweight )"
                    } else {
                        bmi.setTextColor(Color.MAGENTA)
                        remarks.text = "( Obese )"
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        submit_physical.setOnClickListener {
            val editor = sp.edit()
//            editor.putString("age", age.text.toString())
            editor.putString("height", height.text.toString())
            editor.putString("weight", weight.text.toString())
            editor.apply()
            editor.commit()
            Toast.makeText(this, "Successfully Submitted!", Toast.LENGTH_SHORT).show()
            finish()
        }

//        if (sp.contains("age")){
//            age.setText(sp.getString("age", "")!!.toString())
//        }
        if (sp.contains("height")) {
            height.setText(sp.getString("height", "")!!.toString())
        }
        if (sp.contains("weight")) {
            weight.setText(sp.getString("weight", "")!!.toString())
        }
    }
}