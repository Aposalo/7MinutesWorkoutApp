package eu.tutorials.a7_minutesworkoutapp

data class ExerciseModel(val id: Int,
                         val name: String,
                         val image: Int,
                         val isCompleted: Boolean = false,
                         val isSelected: Boolean = false){

    val exerciseTitle = "$GET_READY_FOR $name"
}
