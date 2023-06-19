package pt.ipg.acessoriosautomoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipg.acessoriosautomoveis.databinding.FragmentEliminarAcesIntBinding


class EliminarAcesIntFragment : Fragment() {
    private var _binding: FragmentEliminarAcesIntBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarAcesIntBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.action_eliminar -> {
                eliminar()
                true
            }

            R.id.action_cancelar -> {
                voltaListaAceInt()
                true
            }
            else -> false
        }


    }

    private fun voltaListaAceInt() {
        findNavController().navigate(R.id.action_eliminarAcesIntFragment_to_listaAcessInteriorFragment)
    }

    private fun eliminar() {
        TODO("Not yet implemented")
    }


}