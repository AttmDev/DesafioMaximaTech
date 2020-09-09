package com.attm.maximatech.ui.historico

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.attm.maximatech.R
import com.attm.maximatech.ui.ClientesActivity
import com.attm.maximatech.databinding.FragmentHistoricoBinding
import com.attm.maximatech.utils.InjectorUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_historico.*

class HistoricoFragment : Fragment() {

    val historicoViewModel: HistoricoViewModel by viewModels {
        InjectorUtils.provideHistoricoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHistoricoBinding>(inflater, R.layout.fragment_historico, container, false)
        val adapter = HistoricoAdapter()
        binding.recyclerView.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: HistoricoAdapter) {
        historicoViewModel.dadosHistorico.observe(viewLifecycleOwner) { pedido ->
            adapter.pedidos.apply {
                clear()
                addAll(pedido)
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
        (activity as ClientesActivity).showBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_historico, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.historico_legenda -> Snackbar
                .make(hist_root, R.string.snack_not_implemented_yet, Snackbar.LENGTH_SHORT)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}