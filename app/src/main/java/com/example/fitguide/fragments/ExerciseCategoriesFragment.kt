package com.example.fitguide.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitguide.R
import com.example.fitguide.db.DatabaseProvider

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseCategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseCategoriesFragment : Fragment() {
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_categories, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Categorías de Ejercicios"

        // Obtener la referencia a la base de datos
        val db = DatabaseProvider.getInstance(requireContext())
        val exerciseCategoryDao = db.exerciseCategoryDao()

        // Obtener todos los datos de las categorías
        val exerciseCategories = exerciseCategoryDao.getAll()

        // Mapeo dinámico de los TextViews e ImageViews
        val textViewMap = mapOf(
            "Abdominales" to R.id.abdominalesTextView,
            "Espalda" to R.id.espaldaTextView,
            "Hombros" to R.id.hombrosTextView,
            "Pecho" to R.id.pechoTextView,
            "Piernas" to R.id.piernasTextView
        )

        val imageViewMap = mapOf(
            "Abdominales" to R.id.abdominalesImageView,
            "Espalda" to R.id.espaldaImageView,
            "Hombros" to R.id.hombrosImageView,
            "Pecho" to R.id.pechoImageView,
            "Piernas" to R.id.piernasImageView
        )

        // Asignar los datos a los TextViews y ImageViews
        exerciseCategories.forEach { category ->
            // Verificar si el título de la categoría existe en los mapas
            textViewMap[category.title]?.let { textViewId ->
                view.findViewById<TextView>(textViewId).text = category.title
            }

            imageViewMap[category.title]?.let { imageViewId ->
                view.findViewById<ImageView>(imageViewId)
                    .setImageResource(getImageResource(category.exerciseImage))
            }
        }

        // Método para asignar el listener a los botones
        fun setButtonListener(view: View, buttonId: Int, exerciseType: String) {
            val button = view.findViewById<Button>(buttonId)
            button.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("exerciseType", exerciseType)
                }
                findNavController().navigate(
                    R.id.action_exerciseCategoriesFragment_to_exerciseListFragment,
                    bundle
                )
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

    // Función para obtener el recurso de la imagen según el nombre
    private fun getImageResource(imageName: String): Int {
        // Usamos resources.getIdentifier para buscar el recurso por nombre
        return resources.getIdentifier(imageName, "drawable", requireContext().packageName)
    }
}