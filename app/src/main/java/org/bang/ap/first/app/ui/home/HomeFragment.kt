package org.bang.ap.first.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_view_linear_vertical.view.*
import org.bang.ap.first.app.R

class HomeFragment : Fragment(R.layout.fragment_home) {

//    private var _binding: FragmentHomeBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recycler_view.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        recycler_view.layoutManager = GridLayoutManager(context, 2)

        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recycler_view.adapter = MyAdapter()
    }

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//            val itemView = LayoutInflater.from(context)
//                .inflate(R.layout.item_view_linear_vertical, parent, false)

//            val itemView = LayoutInflater.from(context)
//                .inflate(R.layout.item_view_linear_horizontal, parent, false)

            val itemView =
                LayoutInflater.from(context).inflate(R.layout.item_view_grid, parent, false)

            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.item_image.setImageResource(R.drawable.img_1908)

//            holder.itemView.item_image.setImageDrawable(
//                ContextCompat.getDrawable(
//                    context!!,
//                    R.drawable.img_1908
//                )
//            )
//
//            holder.itemView.item_image.setImageBitmap(
//                BitmapFactory.decodeResource(
//                    context!!.resources,
//                    R.drawable.img_1908
//                )
//            )

            if (position == 0 || position == 3 || position == 4 || position == 7 || position == 9) {
                holder.itemView.item_message.isSingleLine = false
            }

            holder.itemView.item_title.text = " 【$position】移动端架构师体系课"
            holder.itemView.item_message.text = "移动开发'两极分化'，没有差不多的'中间层'，唯有尽早成长为架构师，你的职业道路才能走的更远更稳"
        }

        override fun getItemCount(): Int {
            return 20
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
