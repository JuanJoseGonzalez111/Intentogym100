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
import com.example.fitguide.daos.ExerciseListDao
import com.example.fitguide.db.DatabaseProvider

class ExerciseListFragment : Fragment() {
    private var exerciseType: String? = null
    private lateinit var exerciseListDao: ExerciseListDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exerciseType = it.getString("exerciseType")
        }

        // Inicializar el DAO
        val db = DatabaseProvider.getInstance(requireContext())
        exerciseListDao = db.exerciseListDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar2)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getToolbarTitle(exerciseType)
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        loadExercisesFromDatabase(view)
        setupButtonListeners(view)

        return view
    }

    private fun getCategoryId(exerciseType: String?): Int {
        return when (exerciseType) {
            "abdominales" -> 1
            "espalda" -> 2
            "hombros" -> 3
            "pecho" -> 4
            "piernas" -> 5
            else -> 1
        }
    }

    private fun loadExercisesFromDatabase(view: View) {
        val categoryId = getCategoryId(exerciseType)
        val exercises = exerciseListDao.getExercisesByCategoryId(categoryId)

        exercises.forEachIndexed { index, exercise ->
            setText(view, getTextViewId(index), exercise.name)
            setImage(view, getImageViewId(index), getImageResource(exercise.imageResId))
        }
    }

    private fun setupButtonListeners(view: View) {
        val categoryId = getCategoryId(exerciseType)
        val exercises = exerciseListDao.getExercisesByCategoryId(categoryId)

        exercises.forEachIndexed { index, exercise ->
            val buttonId = getButtonId(index)
            view.findViewById<Button>(buttonId).setOnClickListener {
                navigateToDetails(exercise.imageResId)
            }
        }
    }

    private fun getImageResource(imageName: String): Int {
        // Obtén el ID de recurso usando el nombre de la imagen almacenada en la base de datos.
        return resources.getIdentifier(imageName, "drawable", requireContext().packageName)
    }

    private fun getToolbarTitle(exerciseType: String?): String {
        return when (exerciseType) {
            "abdominales" -> "Ejercicios de Abdominales"
            "espalda" -> "Ejercicios de Espalda"
            "hombros" -> "Ejercicios de Hombros"
            "pecho" -> "Ejercicios de Pecho"
            "piernas" -> "Ejercicios de Piernas"
            else -> "Lista de Ejercicios"
        }
    }

    private fun navigateToDetails(exerciseDetail: String) {
        val bundle = Bundle().apply {
            putString("exerciseDetail", exerciseDetail)
            putString("exerciseType", exerciseType)
        }
        findNavController().navigate(
            R.id.action_exerciseListFragment_to_exerciseDetailsFragment,
            bundle
        )
    }

    private fun getButtonId(index: Int): Int {
        return when (index) {
            0 -> R.id.detailButton
            1 -> R.id.detailButton1
            2 -> R.id.detailButton2
            3 -> R.id.detailButton3
            4 -> R.id.detailButton4
            else -> throw IllegalArgumentException("Invalid index")
        }
    }

    private fun getTextViewId(index: Int): Int {
        return when (index) {
            0 -> R.id.textView
            1 -> R.id.textView1
            2 -> R.id.textView2
            3 -> R.id.textView3
            4 -> R.id.textView4
            else -> throw IllegalArgumentException("Invalid index")
        }
    }

    private fun getImageViewId(index: Int): Int {
        return when (index) {
            0 -> R.id.exerciseImageView
            1 -> R.id.exerciseImageView1
            2 -> R.id.exerciseImageView2
            3 -> R.id.exerciseImageView3
            4 -> R.id.exerciseImageView4
            else -> throw IllegalArgumentException("Invalid index")
        }
    }

    private fun setText(view: View, textViewId: Int, text: String) {
        val textView = view.findViewById<TextView>(textViewId)
        textView.text = text
    }

    private fun setImage(view: View, imageViewId: Int, drawableId: Int) {
        val imageView = view.findViewById<ImageView>(imageViewId)
        imageView.setImageResource(drawableId)
    }

    companion object {
        @JvmStatic
        fun newInstance(exerciseType: String) = ExerciseListFragment().apply {
            arguments = Bundle().apply {
                putString("exerciseType", exerciseType)
            }
        }
    }
}