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

class CustomAdapter(private val appContext: Context, private val surveys: ArrayList<Survey>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return surveys.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = inflater.inflate(R.layout.activity_listview, parent, false)
        var context = appContext

        val titles = view.findViewById<TextView>(R.id.textViewTitle)
        val sDate = view.findViewById<TextView>(R.id.textViewStartDate)
        val eDate = view.findViewById<TextView>(R.id.textViewEndDate)

        titles.text = surveys[position].title
        sDate.text = surveys[position].startDate
        eDate.text = surveys[position].endDate

        val edit = view.findViewById<Button>(R.id.buttonEdit_)

        edit.setOnClickListener {
            val intent = Intent(context, EditSurveyActivity::class.java).apply {
                putExtra("Survey", surveys[position])
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        val delete = view.findViewById<Button>(R.id.buttonDelete)

        delete.setOnClickListener {
            val intent = Intent(context, DeleteSurveyActivity::class.java).apply {
                putExtra("Survey", surveys[position])
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        return view
    }

}
