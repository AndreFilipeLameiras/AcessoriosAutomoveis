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
import pt.ipg.acessoriosautomoveis.databinding.FragmentEliminarAcesIntBinding


class EliminarAcesIntFragment : Fragment() {
    private lateinit var acesInt: AcessInter

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


        acesInt = EliminarAcesIntFragmentArgs.fromBundle(requireArguments()).acessorioInte

        binding.textViewNomeAcesIn.text = acesInt.nome
        binding.textViewClasseAceInt.text = acesInt.classe
        binding.textViewDescricaoAceInt.text = acesInt.descricao
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
        val enderecoAcesInt = Uri.withAppendedPath(CarroContentProvider.ENDERECO_ACESSiNTER, acesInt.id.toString())

        val numAceIntEliminados = requireActivity().contentResolver.delete(enderecoAcesInt, null, null)

        if(numAceIntEliminados == 1){
            Toast.makeText(requireContext(), getString(R.string.acessorio_eliminado_com_sucesso), Toast.LENGTH_LONG).show()
            voltaListaAceInt()
        }else{
            Snackbar.make(binding.textViewNomeAcesIn, getString(R.string.erro_eliminar_acessorio),Snackbar.LENGTH_INDEFINITE)
        }
    }


}