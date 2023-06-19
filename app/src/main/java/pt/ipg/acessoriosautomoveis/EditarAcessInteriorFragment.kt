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
import pt.ipg.acessoriosautomoveis.databinding.FragmentEditarAcessInteriorBinding


class EditarAcessInteriorFragment : Fragment() {
    private var acesInt: AcessInter?= null

    private var _binding: FragmentEditarAcessInteriorBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditarAcessInteriorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_guardar_cancelar

        val acessInter = EditarAcessInteriorFragmentArgs.fromBundle(requireArguments()).acessInt

        if (acesInt != null) {
            binding.editTextNomeInterior.setText(acesInt!!.nome)
            binding.editTextMarcaInterior.setText(acesInt!!.classe)
            binding.editTextDescricao.setText(acesInt!!.descricao)

        }

        this.acesInt = acessInter

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
                voltaListaAcesInter()
                true
            }
            else -> false
        }
    }

    private fun voltaListaAcesInter() {
        findNavController().navigate(R.id.action_editarAcessInteriorFragment_to_listaAcessInteriorFragment)
    }

    private fun guardar() {
        val nome = binding.editTextNomeInterior.text.toString()
        if (nome.isBlank()){
            binding.editTextNomeInterior.error = getString(R.string.nome_obrigatorio)
            binding.editTextNomeInterior.requestFocus()
            return
        }

        val classe = binding.editTextMarcaInterior.text.toString()
        if (classe.isBlank()){
            binding.editTextMarcaInterior.error = getString(R.string.classe_obrigatoria)
            binding.editTextMarcaInterior.requestFocus()
            return
        }

        val descricao = binding.editTextDescricao.text.toString()
        if( descricao.isBlank()){
            binding.editTextDescricao.error = getString(R.string.descricao_obrigatoria)
            binding.editTextDescricao.requestFocus()
            return
        }

        if(acesInt == null){
            val acessInter = AcessInter(
                nome,
                classe,
                descricao
            )
            insereAcessInt(acessInter)
        }else {
            val acessInt = acesInt!!
            acessInt.nome = nome
            acessInt.classe = classe
            acessInt.descricao = descricao

            alteraAcessInt(acessInt)
        }



    }

    private fun alteraAcessInt(acessInt: AcessInter) {

        val enderecoAcessInt = Uri.withAppendedPath(CarroContentProvider.ENDERECO_ACESSiNTER, acessInt.id.toString())
        val acessIntAlterados = requireActivity().contentResolver.update(enderecoAcessInt, acessInt.toContentValues(), null, null)

        if (acessIntAlterados == 1) {
            Toast.makeText(
                requireContext(),
                getString(R.string.acessorio_int_guardado_com_sucesso),
                Toast.LENGTH_SHORT
            ).show()
            voltaListaAcesInter()
        }else {
            binding.editTextNomeInterior.error = getString(R.string.erro_guadar_acessorio_interior)
        }
    }

    private fun insereAcessInt(acessInter: AcessInter) {
        val id = requireActivity().contentResolver.insert(
            CarroContentProvider.ENDERECO_ACESSiNTER,
            acessInter.toContentValues()
        )

        if (id == null){
            binding.editTextNomeInterior.error = getString(R.string.erro_guadar_acessorio_interior)
            return
        }



        Toast.makeText(requireContext(), getString(R.string.acessorio_int_guardado_com_sucesso), Toast.LENGTH_SHORT).show()
        voltaListaAcesInter()
    }

}