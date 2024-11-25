package com.example.fitguide.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitguide.dataclasses.ExerciseDetails
import com.example.fitguide.R
import com.example.fitguide.databinding.FragmentExerciseDetailsBinding

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_details, container, false)

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