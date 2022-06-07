package com.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotificationClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
    private val binding get() = dataBinding!!
    var studentId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_student_detail, container, false)

        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail,
            container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.listener = this

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null) {
            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }
        viewModel.fetch(studentId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            dataBinding.student = it
//            editID.setText(it.id)
//            editName.setText(it.name)
//            editDOB.setText(it.dob)
//            editPhone.setText(it.phone)
//            imageView.loadImage(it.photoURL.toString(), progressBar2)

           // var student = it
//            buttonNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_person_24)
//                    }
//            }
        }
    }

    override fun onButtonNotification(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        MainActivity.showNotification(v.tag.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
    }
}