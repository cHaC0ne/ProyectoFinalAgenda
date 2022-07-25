package es.ipow.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.ipow.agenda.core.GetSpinner
import es.ipow.agenda.databinding.FragmentDeleteBinding

class deleteFragment : Fragment() {
    private var _b : FragmentDeleteBinding? = null
    private val b get() = _b!!
    private val spnOpt = arrayOf("")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentDeleteBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrLenguajes = arrayOf("Python", "C++", "Java", "Kotlin", "PHP", "Eiffel", "Pascal", "Basic")
        GetSpinner(b.spinnerContactDelete, spnOpt, arrLenguajes)
    }
}