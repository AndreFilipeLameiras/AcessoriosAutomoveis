package pt.ipg.acessoriosautomoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.acessoriosautomoveis.databinding.FragmentListaCarrosBinding



/**
 * A simple [Fragment] subclass.
 * Use the [ListaCarrosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaCarrosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentListaCarrosBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_carros, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterCarros = AdapterCarros()
        binding.recyclerViewCarros.adapter = adapterCarros
        binding.recyclerViewCarros.layoutManager = LinearLayoutManager(requireContext())
        
    }

    companion object {

    }
}