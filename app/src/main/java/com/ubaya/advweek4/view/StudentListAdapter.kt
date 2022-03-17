package com.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4.R
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter (val studentList: ArrayList<Student>) :
RecyclerView.Adapter<StudentListAdapter.StudentViewholder>() {
    class StudentViewholder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewholder(view)
    }

    override fun onBindViewHolder(holder: StudentViewholder, position: Int) {
        val student = studentList[position]
        with (holder.view) {
            textID.text = student.id
            textName.text = student.name
            imageView2.loadImage(studentList[position].photoURL.toString(), holder.view.progressBar)
            buttonDetail.setOnClickListener {
                val action = StudentListFragmentDirections.actionStudentDetail(student.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}