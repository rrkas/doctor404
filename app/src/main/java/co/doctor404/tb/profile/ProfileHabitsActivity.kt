package co.doctor404.tb.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import co.doctor404.tb.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main_heart_rate.view.*
import kotlinx.android.synthetic.main.activity_profile_habits.*


class ProfileHabitsActivity : AppCompatActivity() {

    var items: ArrayList<String>? = null
    var adapter: BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_habits)

        add_habit.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Add Habit")
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)
            builder.setPositiveButton("OK") { _, _ -> addHabit(input.text.toString()) }
            builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            builder.show()
        }

        items = getAllHabits()
        adapter = HabitAdapter(items!!)
        habit_list.adapter = adapter
    }

    private fun addHabit(text: String) {
        items!!.add(text)
        writeAllHabits(items!!)
        adapter!!.notifyDataSetChanged()
    }

    private fun getAllHabits(): ArrayList<String> {
        val sp = getSharedPreferences("habits", MODE_PRIVATE)
        if (sp.contains("list")) {
            val arrayHabitType = object : TypeToken<ArrayList<String>>() {}.type
            return Gson().fromJson(sp.getString("list", ""), arrayHabitType)
        }
        return arrayListOf()
    }

    private fun writeAllHabits(list: ArrayList<String>) {
        val sp = getSharedPreferences("habits", MODE_PRIVATE)
        val arrayHabitType = object : TypeToken<ArrayList<String>>() {}.type
        val editor = sp.edit()
        editor.putString("list", Gson().toJson(list, arrayHabitType))
        editor.apply()
        editor.commit()
    }

    inner class HabitAdapter(val list: ArrayList<String>) : BaseAdapter() {
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(p0: Int): Any {
            return list[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        @SuppressLint("InflateParams", "ViewHolder")
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val item = list[p0]
            val view = LayoutInflater.from(p2!!.context).inflate(R.layout.profile_habit_row, null)
            view.findViewById<Button>(R.id.habit_delete).setOnClickListener {
                items!!.removeAt(p0)
                adapter!!.notifyDataSetChanged()
                writeAllHabits(items!!)
            }
            view.findViewById<TextView>(R.id.habit_text).text = item
            return view
        }

    }

}