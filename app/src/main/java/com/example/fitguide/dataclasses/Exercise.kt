package com.example.fitguide.dataclasses

import com.example.fitguide.R

data class Exercise(
    val name: String,
    val imageResId: Int
) {
    companion object {
        fun getExercisesByType(exerciseType: String?): List<Exercise> {
            return when (exerciseType) {
                "abdominales" -> listOf(
                    Exercise("Crunch", R.drawable.crunch),
                    Exercise("Escalador", R.drawable.escalador),
                    Exercise("Giro Ruso", R.drawable.giro_ruso),
                    Exercise("Puente", R.drawable.puente),
                    Exercise("Plancha", R.drawable.plancha)
                )
                "espalda" -> listOf(
                    Exercise("Dominadas", R.drawable.dominadas),
                    Exercise("Peso Muerto", R.drawable.peso_muerto),
                    Exercise("Remo Invertido", R.drawable.remo_invertido),
                    Exercise("Remo con Polea", R.drawable.remo_polea),
                    Exercise("Superman", R.drawable.superman)
                )
                "hombros" -> listOf(
                    Exercise("Elevaciones Frontales", R.drawable.elevaciones_frontales),
                    Exercise("Elevaciones Laterales", R.drawable.elevaciones_laterales),
                    Exercise("Press con barra", R.drawable.press_barra),
                    Exercise("Press con mancuerna", R.drawable.press_mancuernas),
                    Exercise("Remo Vertical", R.drawable.remo_vertical)
                )
                "pecho" -> listOf(
                    Exercise("Apertura con mancuernas", R.drawable.apertura_mancuernas),
                    Exercise("Cruce con poleas", R.drawable.cruce_poleas),
                    Exercise("Flexiones", R.drawable.flexiones),
                    Exercise("Press en Banca", R.drawable.press_banca),
                    Exercise("Press inclinado", R.drawable.press_inclinado_mancuernas)
                )
                "piernas" -> listOf(
                    Exercise("Extensión de rodillas", R.drawable.extension_rodillas),
                    Exercise("Prensa de piernas", R.drawable.prensa_piernas),
                    Exercise("Sentadillas", R.drawable.sentadillas),
                    Exercise("Step-Up", R.drawable.step_up),
                    Exercise("Zancadas", R.drawable.zancadas)
                )
                else -> emptyList()
            }
        }
    }
}
