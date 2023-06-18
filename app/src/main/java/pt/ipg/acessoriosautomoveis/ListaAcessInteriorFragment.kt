package pt.ipg.acessoriosautomoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.acessoriosautomoveis.databinding.FragmentListaAcessInteriorBinding



/**
 * A simple [Fragment] subclass.
 * Use the [ListaAcessInteriorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaAcessInteriorFragment : Fragment() {
    private var _binding: FragmentListaAcessInteriorBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_acess_interior, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterAcessInter = AdapterAcessInter()
        binding.recyclerViewAcessInt.adapter = adapterAcessInter
        binding.recyclerViewAcessInt.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}