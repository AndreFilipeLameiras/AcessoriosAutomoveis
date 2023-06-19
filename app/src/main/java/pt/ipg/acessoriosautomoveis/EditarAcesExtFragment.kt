package pt.ipg.acessoriosautomoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import pt.ipg.acessoriosautomoveis.databinding.FragmentEditarAcesExtBinding


class EditarAcesExtFragment : Fragment() {

    private var acessExt: AcessExter?= null

    private var _binding: FragmentEditarAcesExtBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditarAcesExtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_guardar_cancelar

        val acessExte = EditarAcesExtFragmentArgs.fromBundle(requireArguments()).acessExt

        if(acessExt != null){
            binding.editTextNomeExt.setText(acessExt!!.nome)
            binding.editTextMarcaExt.setText(acessExt!!.categoria)
            binding.ediTextCorExt.setText(acessExt!!.cor)
        }

        this.acessExt = acessExte


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaAcesExt()
                true
            }
            else -> false
        }
    }

    private fun voltaListaAcesExt() {
        findNavController().navigate(R.id.action_editarAcesExtFragment_to_listaAcessExtFragment)
    }

    private fun guardar() {
        val nome = binding.editTextNomeExt.text.toString()
        if (nome.isBlank()){
            binding.editTextNomeExt.error = getString(R.string.nome_obrigatorio)
            binding.editTextNomeExt.requestFocus()
            return
        }

        val categoria = binding.editTextMarcaExt.text.toString()
        if (categoria.isBlank()){
            binding.editTextMarcaExt.error = getString(R.string.categoria_obrigatoria)
            binding.editTextMarcaExt.requestFocus()
            return
        }

        val cor = binding.ediTextCorExt.text.toString()
        if( cor.isBlank()){
            binding.ediTextCorExt.error = getString(R.string.cor_obrigatoria)
            binding.ediTextCorExt.requestFocus()
            return
        }


        val acessExter = AcessExter(
            nome,
            categoria,
            cor
        )

        val id = requireActivity().contentResolver.insert(
            CarroContentProvider.ENDERECO_ACESSEXTER,
            acessExter.toContentValues()
        )

        if (id == null){
            binding.editTextNomeExt.error = getString(R.string.erro_guadar_acessorio_exterior)
            return
        }
        Toast.makeText(requireContext(), getString(R.string.acessorio_ext_guardado_com_sucesso), Toast.LENGTH_SHORT).show()
        voltaListaAcesExt()
    }
}