package org.bang.ap.first.app

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_second.*
import org.bang.ap.first.app.components.SecondFragment

class SecondActivity : AppCompatActivity() {
    private var tab1Fragment: SecondFragment? = null

    private var tab2Fragment: SecondFragment? = null

    private var tab3Fragment: SecondFragment? = null

    private var showFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val extraData = intent.getStringExtra("extra_data")
//        val extraIntData = intent.getIntExtra("extra_int_data", 0)
//
//        val textView = TextView(this)
//        textView.text = "SecondActivity.${extraData}.${extraIntData}"
//        textView.gravity = Gravity.CENTER
//        textView.setOnClickListener {
//            val resultIntent = Intent()
//            resultIntent.putExtra("result_extra_string", "result_extra_string")
//            resultIntent.putExtra("result_extra_int", 1000)
//            setResult(Activity.RESULT_OK, resultIntent)
//            finish()
//        }
//        setContentView(textView)

        Log.e("SecondActivity", "onCreate")

//        setContentView(R.layout.activity_second)
//        val fragment = SecondFragment()
//        val bundle = Bundle()
//        bundle.putInt("int_extra", 100)
//        bundle.putString("string_extra", "string_extra_value")
//        fragment.arguments = bundle
//        val ft = supportFragmentManager.beginTransaction()
//        ft.add(R.id.container, fragment)
//        ft.commit()

        setContentView(R.layout.activity_second)
        toggle_group.addOnButtonCheckedListener { group, checkedId, _ ->
            val childCount = group.childCount
            var selectIndex = 0
            for (index in 0 until childCount) {
                val button = group.getChildAt(index) as MaterialButton
                if (button.id == checkedId) {
                    // 选中的按钮
                    selectIndex = index
                    button.setTextColor(Color.RED)
                    button.iconTint = ColorStateList.valueOf(Color.RED)
                } else {
                    button.setTextColor(Color.BLACK)
                    button.iconTint = ColorStateList.valueOf(Color.BLACK)
                }
            }
            switchFragment(selectIndex)
        }
        toggle_group.check(R.id.tab1)
    }

    override fun onStart() {
        super.onStart()

        Log.e("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("SecondActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        Log.e("SecondActivity", "onRestart")
    }


    private fun switchFragment(selectIndex: Int) {
        val fragment = when (selectIndex) {
            0 -> {
                if (tab1Fragment == null) {
                    tab1Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab1")
                    tab1Fragment!!.arguments = bundle
                }
                tab1Fragment
            }
            1 -> {
                if (tab2Fragment == null) {
                    tab2Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab2")
                    tab2Fragment!!.arguments = bundle
                }
                tab2Fragment
            }
            2 -> {
                if (tab3Fragment == null) {
                    tab3Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab3")
                    tab3Fragment!!.arguments = bundle
                }
                tab3Fragment
            }
            else -> {
                throw IllegalArgumentException("下标不符合预期")
            }
        } ?: return

        val ft = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            ft.add(R.id.container, fragment)
        }
        ft.show(fragment)
        if (showFragment != null) {
            ft.hide(showFragment!!)
        }
        showFragment = fragment
        ft.commitAllowingStateLoss()
    }
}
