package org.bang.ap.first.app.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.e("SecondFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("SecondFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("SecondFragment", "onCreateView")

        val textView = TextView(context)
        textView.text = "SecondFragment"
        textView.gravity = Gravity.CENTER
        return textView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val intValue = arguments?.getInt("int_extra")
//        val stringValue = arguments?.getString("string_extra")
//
//        val textView = view as TextView
//        textView.text = "${intValue}.${stringValue}"

        val textView = view as TextView
        val tab = arguments?.getString("tab")
        textView.text = "$tab"

        textView.setOnClickListener {
//            val intent = Intent(context, TestServiceActivity::class.java)

            val intent = Intent(context, TestBroadcastReceiverActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.e("SecondFragment", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()

        Log.e("SecondFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("SecondFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e("SecondFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e("SecondFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView() // onCreateView?????????view?????????????????????????????????????????????

        Log.e("SecondFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy() // ??????fragment?????????????????????????????????

        Log.e("SecondFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()

        Log.e("SecondFragment", "onDetach")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        // ????????????activity????????????fragment????????????????????????show-hide
        // hidden: ??????fragment?????????????????????true???ft.show
        // hidden: ??????fragment??????????????????false???ft.hide
        Log.e("SecondFragment", "onHiddenChanged: ${arguments?.getString("tab")}" + ".${hidden}")
    }
}
