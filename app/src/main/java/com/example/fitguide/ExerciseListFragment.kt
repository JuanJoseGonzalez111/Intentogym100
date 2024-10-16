package com.example.fitguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExerciseListFragment : Fragment() {
    private var exerciseType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exerciseType = it.getString("exerciseType")
        }
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

        // Configurar el listener para el botón de retroceso en la barra de herramientas
        toolbar.setNavigationOnClickListener {
            val fragment = ExerciseCategoriesFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, fragment)?.commit()
        }

        loadExercises(view)
        setupButtonListeners(view)

        return view
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

    private fun setupButtonListeners(view: View) {
        val exerciseMap = when (exerciseType) {
            "abdominales" -> listOf("crunch", "escalador", "giro_ruso", "puente", "plancha")
            "espalda" -> listOf("dominadas", "peso_muerto", "remo_invertido", "remo_polea", "superman")
            "hombros" -> listOf("elevaciones_frontales", "elevaciones_laterales", "press_barra", "press_mancuerna", "remo_vertical")
            "pecho" -> listOf("apertura_mancuerna", "cruce_polea", "flexiones", "press_banca", "press_inclinado_mancuernas")
            "piernas" -> listOf("extension_rodillas", "prensa_piernas", "sentadillas", "step_up", "zancadas")
            else -> emptyList()
        }

        exerciseMap.forEachIndexed { index, exerciseDetail ->
            val buttonId = getButtonId(index)
            view.findViewById<Button>(buttonId).setOnClickListener {
                navigateToDetails(exerciseDetail)
            }
        }
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

    private fun navigateToDetails(exerciseDetail: String) {
        val fragment = ExerciseDetailsFragment.newInstance(exerciseDetail, exerciseType ?: "")
        fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, fragment)?.commit()
    }

    private fun loadExercises(view: View) {
        val exercises = Exercise.getExercisesByType(exerciseType)

        for (i in exercises.indices) {
            setText(view, getTextViewId(i), exercises[i].name)
            setImage(view, getImageViewId(i), exercises[i].imageResId)
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
