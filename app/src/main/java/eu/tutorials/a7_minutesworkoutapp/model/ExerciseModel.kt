package eu.tutorials.a7_minutesworkoutapp.model

data class ExerciseModel(val id: Int,
                         val name: String,
                         val image: Int,
                         val isCompleted: Boolean = false,
                         val isSelected: Boolean = false)
