package com.example.fitguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseCategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseCategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_categories, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Categorías de Ejercicios"

        // Método para asignar imágenes a los ImageView
        fun setImage(view: View, imageViewId: Int, drawableId: Int) {
            val imageView = view.findViewById<ImageView>(imageViewId)
            imageView.setImageResource(drawableId)
        }

        setImage(view, R.id.abdominalesImageView, R.drawable.abdominales)
        setImage(view, R.id.espaldaImageView, R.drawable.espalda)
        setImage(view, R.id.hombrosImageView, R.drawable.hombros)
        setImage(view, R.id.pechoImageView, R.drawable.pecho)
        setImage(view, R.id.piernasImageView, R.drawable.piernas)

        //Método para asignar el listener a los botones
        fun setButtonListener(view: View, buttonId: Int, exerciseType: String) {
            val button = view.findViewById<Button>(buttonId)
            button.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("exerciseType", exerciseType)
                }
                findNavController().navigate(R.id.action_exerciseCategoriesFragment_to_exerciseListFragment, bundle)
            }
        }

        // Asignar el listener a los botones con el tipo de ejercicio correspondiente
        setButtonListener(view, R.id.abdominalesButton, "abdominales")
        setButtonListener(view, R.id.espaldaButton, "espalda")
        setButtonListener(view, R.id.hombrosButton, "hombros")
        setButtonListener(view, R.id.pechoButton, "pecho")
        setButtonListener(view, R.id.piernasButton, "piernas")
        return view
    }
}