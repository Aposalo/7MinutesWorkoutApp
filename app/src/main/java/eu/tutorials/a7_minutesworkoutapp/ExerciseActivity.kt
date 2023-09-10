package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityExerciseBinding

    private var restTimer: CountDownTimer = WorkoutTimer(11000)
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer = ExerciseTimer(31000)
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            setSupportActionBar(toolbarExercise)
            toolbarExercise.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setRestProgressBar()
    }

    private fun stopRestTimer() {
        restTimer.cancel()
        restProgress = 0
    }

    private fun stopExerciseTimer() {
        exerciseTimer.cancel()
        exerciseProgress = 0
    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress // Sets the current progress to the specified value.
        restTimer = restTimer.start()
    }

    private fun setupExerciseView() {
        stopRestTimer()
        binding.flProgressBar.visibility = View.INVISIBLE
        binding.tvTitle.text = getString(R.string.exercise_name)
        binding.flExerciseView.visibility = View.VISIBLE
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = exerciseTimer.start()
    }

    public override fun onStop() {
        stopRestTimer()
        stopExerciseTimer()
        super.onStop()
    }

    inner class ExerciseTimer(millisInFuture: Long) : CountDownTimer(millisInFuture, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val newProgress = 30 - exerciseProgress
            binding.progressBarExercise.progress = newProgress
            binding.tvTimerExercise.text = newProgress.toString()
            exerciseProgress++
        }

        override fun onFinish() {
            Toast.makeText(
                this@ExerciseActivity,
                "This is 30 seconds completed so now we will add all the exercises.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class WorkoutTimer(millisInFuture: Long) : CountDownTimer(millisInFuture, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val newProgress = 10 - restProgress
            binding.progressBar.progress = newProgress
            binding.tvTimer.text = newProgress.toString()
            restProgress++
        }

        override fun onFinish() {
            setupExerciseView()
        }
    }
}