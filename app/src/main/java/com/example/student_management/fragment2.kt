package com.example.student_management

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.student_management.database.Student_Database
import com.example.student_management.model.Student_Entity
import kotlinx.android.synthetic.main.activity_fragment2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class fragment2(
) : AppCompatActivity(),CoroutineScope {


    private var noteDB: Student_Database ?= null

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle("Chỉnh sửa sinh viên");
        setContentView(R.layout.activity_fragment2)
        val colorDrawable = ColorDrawable(Color.parseColor("#F50057"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        mJob = Job()
        noteDB = Student_Database.getDatabase(this)
//      var notes = emptyList<Student_Entity>()

        val id:Int = Integer.parseInt(intent.extras?.get("student_id").toString())
        val name: String = intent.extras?.get("student_name").toString()
        val grade: String = intent.extras?.get("student_grade").toString()
        val email: String = intent.extras?.get("email").toString()
        val diachi: String = intent.extras?.get("diachi").toString()
//        var strUser: String? = intent.getStringExtra("student_name") // 2
        edt_name.setText(name)
        edt_grade.setText(grade)
        edt_email.setText(email)
        edt_address.setText(diachi)
        btn_update.setOnClickListener{
            launch {
                val strname: String = edt_name.text.toString()
                val strgrade: String = edt_grade.text.toString()
                val stremail: String = edt_email.text.toString()
                val strdiachi: String = edt_address.text.toString()

                if (strname =="" || strgrade == "") {
                    Toast.makeText(this@fragment2,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show()
                } else {
                    noteDB?.studentDao()?.update(Student_Entity(id = id,name=strname,grade = strgrade,email = stremail,address = strdiachi))
                    Toast.makeText(this@fragment2,"Cập Nhật Thành Công",Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    setResult(RESULT_OK,intent)
                    finish()
                }

            }
        }

    }


}