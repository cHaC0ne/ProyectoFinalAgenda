package es.ipow.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import es.ipow.agenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.bottonNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itNuevo -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.newFragment)
                    true
                }
                R.id.itDelete -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.deleteFragment)
                    true
                }
                R.id.itUpdate -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.updateFragment)
                    true
                }
                R.id.itList -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.listFragment)
                    true
                } else -> false
            }
        }
    }
}