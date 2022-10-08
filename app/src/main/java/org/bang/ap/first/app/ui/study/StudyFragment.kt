package org.bang.ap.first.app.ui.study

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_study.*
import kotlinx.android.synthetic.main.item_fragment_study.view.*
import org.bang.ap.first.app.R
import org.bang.ap.first.app.http.ApRetrofit
import org.bang.ap.first.app.http.ApiService
import org.bang.ap.first.app.http.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudyFragment : Fragment(R.layout.fragment_study) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studyAdapter = StudyAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = studyAdapter

        tab_add_course.setOnClickListener {
            val course = Course(
                "Android学习基础",
                "https://www.songyubao.com/static/book/assets/icon-android.jpeg",
                "100%",
                "Android RecyclerView基础学习"
            )

            studyAdapter.addCourse(course)
            recycler_view.scrollToPosition(0)
        }

        tab_delete_course.setOnClickListener {
            studyAdapter.deleteCourse(0)
        }

        tab_update_course.setOnClickListener {
            studyAdapter.updateCourse(0, "80%")
        }

        ApRetrofit.create(ApiService::class.java)
            .getStudy()
            .enqueue(object : Callback<List<Course>> {
                override fun onResponse(
                    call: Call<List<Course>>,
                    response: Response<List<Course>>
                ) {
                    Log.e(
                        "getStudy",
                        "getStudy onResponse: ${response.body() ?: "response is null"}"
                    )
                    response.body()?.let { studyAdapter.setData(it) }
                }

                override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                    Log.e("getStudy", "getStudy onFailure: ${t.message ?: "unknown error"}")
                }
            })
    }

    inner class StudyAdapter : RecyclerView.Adapter<StudyAdapter.StudyViewHolder>() {
        private val courses = mutableListOf<Course>()

        inner class StudyViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): StudyViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_fragment_study, parent, false)
            return StudyViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
            val course = courses[position]

//            holder.itemView.item_course_poster.setImageResource(R.drawable.img_1908)
            val options = RequestOptions().transform(RoundedCorners(20))
            Glide.with(context!!).load(course.poster).apply(options)
                .into(holder.itemView.item_course_poster)

            holder.itemView.item_course_title.text = course.title
            holder.itemView.item_course_label.text = course.label
            holder.itemView.item_course_progress.text = "已学${course.progress}"
        }

        override fun getItemCount(): Int {
            return courses.size
        }

        fun setData(data: List<Course>) {
            if (data.isNotEmpty()) {
                courses.addAll(data)
                notifyDataSetChanged() // 会重新执行getItemCount和onBindViewHolder
            }
        }

        fun addCourse(course: Course) {
            courses.add(0, course)
            notifyItemInserted(0)

//            notifyDataSetChanged()

//            courses.add(course)
//            notifyItemInserted(courses.size - 1)
        }

        fun deleteCourse(position: Int) {
            courses.removeAt(position)
            notifyItemRemoved(position)

//            notifyDataSetChanged()
        }

        fun updateCourse(position: Int, progress: String) {
            val course = courses[position]
            course.progress = progress
            notifyItemChanged(position)

//            notifyDataSetChanged()
        }
    }
}
