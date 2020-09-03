package com.attm.maximatech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.attm.maximatech.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        hideActionBar()
    }

    override fun onStart() {
        super.onStart()
        launcher_layout.setOnClickListener { openMenuActivity() }
        initOpenMenuActivityTimer()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
        actionBar?.hide()
    }

    private fun initOpenMenuActivityTimer() {
        Handler().postDelayed({ openMenuActivity() }, 2000)
    }

    private fun openMenuActivity() {
        startActivity(Intent(this, MenuActivity::class.java))
    }
}