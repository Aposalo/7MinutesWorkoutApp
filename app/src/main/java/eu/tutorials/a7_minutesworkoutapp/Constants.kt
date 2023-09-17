package eu.tutorials.a7_minutesworkoutapp

private const val JUMPING_JACKS = "Jumping Jacks"
private const val WALL_SIT = "Wall Sit"
private const val PUSH_UP = "Push Up"
private const val ABDOMINAL_CRUNCH = "Abdominal Crunch"
private const val STEP_UP_ONTO_CHAIR = "Step-Up onto Chair"
private const val SQUAT = "Squat"
private const val TRICEP_DIP_ON_CHAIR = "Tricep Dip On Chair"
private const val PLANK = "Plank"
private const val HIGH_KNEES_RUNNING_IN_PLACE = "High Knees Running In Place"
private const val LUNGES = "Lunges"
private const val PUSH_UP_AND_ROTATION = "Push up and Rotation"
private const val SIDE_PLANK = "Side Plank"

object Constants {

    private var id: Int = 1

    private fun generateExerciseModel(exerciseLabel: String, imageIcon: Int): ExerciseModel{
        return ExerciseModel(
            id++,
            exerciseLabel,
            imageIcon
        )
    }

    fun defaultExerciseList(): List<ExerciseModel> {

        return listOf(
            generateExerciseModel(JUMPING_JACKS, R.drawable.ic_jumping_jacks),
            generateExerciseModel(WALL_SIT, R.drawable.ic_wall_sit),
            generateExerciseModel(PUSH_UP, R.drawable.ic_push_up),
            generateExerciseModel(ABDOMINAL_CRUNCH, R.drawable.ic_abdominal_crunch),
            generateExerciseModel(STEP_UP_ONTO_CHAIR, R.drawable.ic_step_up_onto_chair),
            generateExerciseModel(SQUAT, R.drawable.ic_squat),
            generateExerciseModel(TRICEP_DIP_ON_CHAIR, R.drawable.ic_triceps_dip_on_chair),
            generateExerciseModel(PLANK, R.drawable.ic_plank),
            generateExerciseModel(HIGH_KNEES_RUNNING_IN_PLACE, R.drawable.ic_high_knees_running_in_place),
            generateExerciseModel(LUNGES, R.drawable.ic_lunge),
            generateExerciseModel(PUSH_UP_AND_ROTATION, R.drawable.ic_push_up_and_rotation),
            generateExerciseModel(SIDE_PLANK, R.drawable.ic_side_plank)
        )
    }
}