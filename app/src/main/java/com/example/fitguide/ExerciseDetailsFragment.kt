package com.example.fitguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fitguide.databinding.FragmentExerciseDetailsBinding
import com.example.fitguide.R
import androidx.navigation.fragment.findNavController

class ExerciseDetailsFragment : Fragment() {
    private var exerciseDetail: String? = null
    private var exerciseType: String? = null
    private lateinit var binding: FragmentExerciseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exerciseDetail = it.getString("exerciseDetail")
            exerciseType = it.getString("exerciseType")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_details, container, false)

        val toolbar = binding.toolbar1
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detalle de Ejercicio"
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        loadExerciseDetails()

        return binding.root
    }

    private fun loadExerciseDetails() {
        val exercise = ExerciseDetails.getExerciseDetails(exerciseDetail)

        exercise?.let {
            binding.exercise = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(exerciseDetail: String, exerciseType: String) = ExerciseDetailsFragment().apply {
            arguments = Bundle().apply {
                putString("exerciseDetail", exerciseDetail)
                putString("exerciseType", exerciseType)
            }
        }
    }
}

