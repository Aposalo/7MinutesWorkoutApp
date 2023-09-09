package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityExerciseBinding

    private lateinit var restTimer: CountDownTimer

    private var restProgress = 0

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

    private fun setupRestView() {
        restTimer.cancel()
        restProgress = 0
    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress // Sets the current progress to the specified value.
        restTimer = RestTimer().start()
    }

    public override fun onStop() {
        setupRestView()
        super.onStop()
    }

    inner class RestTimer : CountDownTimer(11000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val newProgress = 10 - restProgress
            binding.progressBar.progress = newProgress
            binding.tvTimer.text = newProgress.toString()
            restProgress++
        }

        override fun onFinish() {
            // When the 10 seconds will complete this will be executed.
            Toast.makeText(
                this@ExerciseActivity,
                "Here now we will start the exercise.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}