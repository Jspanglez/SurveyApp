package com.example.surveyapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.surveyapp.Model.Survey

class StudentCustomAdapter(private val appContext: Context, private val studentSurveys: ArrayList<Survey>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return studentSurveys.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = inflater.inflate(R.layout.activity_student_listview, parent, false)
        var context = appContext

        val titles = view.findViewById<TextView>(R.id.textViewStudentTitle)
        val sDate = view.findViewById<TextView>(R.id.textViewStudentStartDate)
        val eDate = view.findViewById<TextView>(R.id.textViewStudentEndDate)

        titles.text = studentSurveys[position].title
        sDate.text = studentSurveys[position].startDate
        eDate.text = studentSurveys[position].endDate

        val start = view.findViewById<Button>(R.id.buttonBegin)

        start.setOnClickListener {
            val intent = Intent(context, StudentQuestionsActivity::class.java).apply {
                putExtra("Survey", studentSurveys[position])
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        return view
    }

}