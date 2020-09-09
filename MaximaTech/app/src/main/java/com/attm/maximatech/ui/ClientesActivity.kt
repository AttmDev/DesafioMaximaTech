package com.attm.maximatech.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.attm.maximatech.R
import com.attm.maximatech.data.repository.Repository
import kotlinx.android.synthetic.main.activity_clientes.*

class ClientesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)
        setSupportActionBar(toolbar)
        initRepository()
        setupNavigation()
    }

    private fun initRepository() {
        Repository.initialize(this)
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_menu)
                nav_view?.visibility = View.GONE
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph, )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        toolbar.setupWithNavController(navController)
    }


    fun showBottomNavigation()
    {
        nav_view?.visibility = View.VISIBLE
    }

    fun hideBottomNavigation()
    {
       nav_view?.visibility = View.GONE
    }



}