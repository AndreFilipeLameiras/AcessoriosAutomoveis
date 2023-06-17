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
import pt.ipg.acessoriosautomoveis.databinding.FragmentEliminarCarroBinding



/**
 * A simple [Fragment] subclass.
 * Use the [EliminarCarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarCarroFragment : Fragment() {

    private lateinit var carros: Carro

    private var _binding: FragmentEliminarCarroBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarCarroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        carros = EliminarCarroFragmentArgs.fromBundle(requireArguments()).Carro

        binding.textViewMarcas.text = carros.marca
        binding.textViewCor.text = carros.cor
        binding.textViewAcessInter.text = carros.acessInter.nome
        binding.textViewAcessExter.text = carros.acessExter.nome
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu (item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.action_eliminar -> {
                eliminar()
                true
            }
            R.id.action_cancelar -> {
                voltarListaCarros()
                true
            }

            else -> false
        }
    }

    private fun voltarListaCarros() {
        findNavController().navigate(R.id.action_eliminarCarroFragment_to_listaCarrosFragment)
    }

    private fun eliminar() {
        val enderecoCarro = Uri.withAppendedPath(CarroContentProvider.ENDERECO_CARROS, carros.id.toString())
        val numCarrosEliminados = requireActivity().contentResolver.delete(enderecoCarro, null, null)

        if (numCarrosEliminados == 1) {
            Toast.makeText(requireContext(), getString(R.string.carro_eliminado_com_sucesso), Toast.LENGTH_LONG).show()
            voltarListaCarros()
        } else {
            Snackbar.make(binding.textViewMarcas, getString(R.string.erro_eliminar_carro), Snackbar.LENGTH_INDEFINITE)
        }
    }
}