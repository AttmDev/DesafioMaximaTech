package com.attm.maximatech.menu

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.attm.maximatech.R
import com.attm.maximatech.ui.ClientesActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment(), MenuAdapter.Listener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
        setupNetworkStatusCallBack()
    }

    private fun setupAdapter() {
        recycler_view.adapter = MenuAdapter(MenuOptions.getValues(), this)
    }

    private fun setupNetworkStatusCallBack() {
        val cm = context?.getSystemService(ConnectivityManager::class.java)
        cm?.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                network_status.setImageResource(R.drawable.ic_maxima_nuvem_conectado)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                network_status.setImageResource(R.drawable.ic_maxima_nuvem_desconectado)
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as ClientesActivity).hideBottomNavigation()    }

    override fun onItemClick(item: MenuOptionModel) {
        when (item.optionTitle) {
            R.string.menu_opt_cliente -> findNavController().navigate(R.id.action_navigation_menu_dados_cliente)
            else -> {
                Snackbar
                    .make(menu_root, R.string.snack_not_implemented_yet, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}


