package org.bang.ap.first.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.bang.ap.first.app.databinding.ActivityMainBinding
import org.bang.ap.first.app.http.ApOkHttp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        println("where is me ?")
//
//        R.id.text_home.onClick(this) {
//            println("text_home onClick")
//        }

//        setContentView(R.layout.comps_material_button)
//        val toggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggle_group)
//        toggleGroup.addOnButtonCheckedListener { _, _, _ ->
//            Log.e("MainActivity", "isChecked")
//        }

//        ApOkHttp.get()
        ApOkHttp.getAsync()
    }
}

//fun Int.onClick(activity: Activity, click: () -> Unit) {
//    activity.findViewById<View>(this).setOnClickListener {
//        println("onClick")
//        click()
//    }
//}
