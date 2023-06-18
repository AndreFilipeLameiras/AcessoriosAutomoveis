package pt.ipg.acessoriosautomoveis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.acessoriosautomoveis.databinding.FragmentListaAcessExtBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ListaAcessExtFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaAcessExtFragment : Fragment() {
    private var _binding: FragmentListaAcessExtBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_acess_ext, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterAcessExt = AdapterAcessExter()
        binding.recyclerViewAcessExt.adapter = adapterAcessExt
        binding.recyclerViewAcessExt.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}