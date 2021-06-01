package co.doctor404.tb.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.doctor404.tb.R
import kotlinx.android.synthetic.main.activity_profile_others.*
import java.util.*

class ProfileOthersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_others)

        getData()

        profile_submit.setOnClickListener {
            val sp = getSharedPreferences("profile", MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("name", profile_name.text.toString())
            editor.putString("dob", profile_dob.text.toString())
            editor.putString("mail", profile_mail.text.toString())
            editor.putString("phone", profile_phone.text.toString())
            editor.apply()
            editor.commit()
            finish()
        }
    }


    private fun getData() {
        val sp = getSharedPreferences("profile", MODE_PRIVATE)
        if (sp.contains("name")) {
            profile_name.setText(sp.getString("name", ""))
            profile_mail.setText(sp.getString("mail", ""))
            profile_dob.setText(sp.getString("dob", ""))
            profile_phone.setText(sp.getString("phone", ""))
        }
        profile_age.text = (Calendar.getInstance().get(Calendar.YEAR) - 2000).toString()
    }

}