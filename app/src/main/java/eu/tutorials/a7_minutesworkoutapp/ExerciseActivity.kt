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

    private lateinit var exerciseList : List<ExerciseModel>
    private var currentExercisePosition = 0

    private lateinit var exerciseViewModel: ExerciseViewModel

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
        exerciseViewModel = ExerciseViewModel()
        exerciseList = exerciseViewModel.defaultExerciseList()
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

        binding.flRestView.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvExerciseName.visibility = View.INVISIBLE
        binding.flExerciseView.visibility = View.INVISIBLE
        binding.ivImage.visibility = View.INVISIBLE
        binding.tvUpcomingExercise.visibility = View.VISIBLE
        binding.tvUpcomingExerciseLabel.visibility = View.VISIBLE
        binding.tvUpcomingExercise.text = exerciseList[currentExercisePosition].name
        binding.progressBar.progress = restProgress
        restTimer = restTimer.start()
    }

    private fun setupExerciseView() {
        stopRestTimer()
        binding.flRestView.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvExerciseName.visibility = View.VISIBLE
        binding.tvExerciseName.text = exerciseList[currentExercisePosition].name
        binding.flExerciseView.visibility = View.VISIBLE
        binding.ivImage.visibility = View.VISIBLE
        binding.tvUpcomingExercise.visibility = View.INVISIBLE
        binding.tvUpcomingExerciseLabel.visibility = View.INVISIBLE
        binding.ivImage.setImageResource(exerciseList[currentExercisePosition].image)
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
            if(currentExercisePosition < exerciseList.size){
                stopExerciseTimer()
                setRestProgressBar()
            }
            else
            {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Congratulations for finishing the exercise.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
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
            currentExercisePosition++
        }
    }
}