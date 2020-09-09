package com.attm.maximatech.ui.alvara

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.attm.maximatech.R
import com.attm.maximatech.ui.ClientesActivity
import com.attm.maximatech.ui.dados.DadosViewModel

class AlvaraFragment : Fragment() {

    private val alvaraViewModel: DadosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_alvaras, container, false)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as ClientesActivity).showBottomNavigation()
    }
}