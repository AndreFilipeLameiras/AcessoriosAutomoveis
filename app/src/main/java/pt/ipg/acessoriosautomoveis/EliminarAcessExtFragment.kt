package pt.ipg.acessoriosautomoveis

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.acessoriosautomoveis.databinding.FragmentEliminarAcessExtBinding


class EliminarAcessExtFragment : Fragment() {
    private lateinit var acesExt: AcessExter

    private var _binding: FragmentEliminarAcessExtBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarAcessExtBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        acesExt = EliminarAcessExtFragmentArgs.fromBundle(requireArguments()).acessorioExt

        binding.textViewNomeAceExt.text = acesExt.nome
        binding.textViewCategoriaAceExt.text = acesExt.categoria
        binding.textViewCorAceExt.text = acesExt.cor
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun processaOpcaoMenu(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.action_eliminar -> {
                eliminar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaAceExt()
                true
            }
            else -> false
        }


    }

    private fun voltaListaAceExt() {
        findNavController().navigate(R.id.action_eliminarAcessExtFragment_to_listaAcessExtFragment)
    }

    private fun eliminar() {
        val enderecoAcesExt = Uri.withAppendedPath(CarroContentProvider.ENDERECO_ACESSEXTER, acesExt.id.toString())

        val numAceExtEliminados = requireActivity().contentResolver.delete(enderecoAcesExt, null, null)

        if(numAceExtEliminados == 1){
            Toast.makeText(requireContext(), getString(R.string.acessorio_eliminado_com_sucesso), Toast.LENGTH_LONG).show()
            voltaListaAceExt()
        }else{
            Snackbar.make(binding.textViewNomeAceExt, getString(R.string.erro_eliminar_acessorio),
                Snackbar.LENGTH_INDEFINITE)
        }
    }

}