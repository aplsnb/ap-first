package org.bang.ap.first.app.components

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_service.*
import org.bang.ap.first.app.R

class TestServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_service)

        start_service.setOnClickListener {
            val intent = Intent(this, TestService1::class.java)
            startService(intent)
        }

        stop_service.setOnClickListener {
            val intent = Intent(this, TestService1::class.java)
            stopService(intent)
        }
    }
}
