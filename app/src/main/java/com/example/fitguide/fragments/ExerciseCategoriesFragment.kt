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
import com.example.fitguide.entities.ExerciseCategoryData

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

        // Obtener todas las categorías de la base de datos
        val exerciseCategories = exerciseCategoryDao.getAll()

        // Método para asignar dinámicamente los datos a la vista
        populateCategories(view, exerciseCategories)

        return view
    }

    private fun populateCategories(view: View, categories: List<ExerciseCategoryData>) {
        val textViewMap = mapOf(
            R.id.abdominalesTextView to "Abdominales",
            R.id.espaldaTextView to "Espalda",
            R.id.hombrosTextView to "Hombros",
            R.id.pechoTextView to "Pecho",
            R.id.piernasTextView to "Piernas"
        )

        val imageViewMap = mapOf(
            R.id.abdominalesImageView to "Abdominales",
            R.id.espaldaImageView to "Espalda",
            R.id.hombrosImageView to "Hombros",
            R.id.pechoImageView to "Pecho",
            R.id.piernasImageView to "Piernas"
        )

        // Asignar datos a los TextViews e ImageViews
        categories.forEach { category ->
            textViewMap.entries.find { it.value == category.title }?.key?.let { textViewId ->
                view.findViewById<TextView>(textViewId).text = category.title
            }

            imageViewMap.entries.find { it.value == category.title }?.key?.let { imageViewId ->
                view.findViewById<ImageView>(imageViewId)
                    .setImageResource(getImageResource(category.exerciseImage))
            }
        }

        // Asignar listeners a los botones dinámicamente
        categories.forEach { category ->
            val buttonId = getButtonId(category.title)
            buttonId?.let { id ->
                setButtonListener(view, id, category.title.lowercase())
            }
        }
    }

    private fun setButtonListener(view: View, buttonId: Int, exerciseType: String) {
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

    private fun getImageResource(imageName: String): Int {
        return resources.getIdentifier(imageName, "drawable", requireContext().packageName)
    }

    private fun getButtonId(categoryTitle: String): Int? {
        return when (categoryTitle) {
            "Abdominales" -> R.id.abdominalesButton
            "Espalda" -> R.id.espaldaButton
            "Hombros" -> R.id.hombrosButton
            "Pecho" -> R.id.pechoButton
            "Piernas" -> R.id.piernasButton
            else -> null
        }
    }
}
