package eu.tutorials.a7_minutesworkoutapp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var speech : SpeekText

    private lateinit var binding : ActivityExerciseBinding

    private var restTimer: CountDownTimer = WorkoutTimer(11000)
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer = ExerciseTimer(31000)
    private var exerciseProgress = 0

    private lateinit var exerciseList : List<ExerciseModel>
    private var currentExercisePosition = 0

    private lateinit var exerciseViewModel: ExerciseViewModel

    private lateinit var soundPlayer: SoundPlayerWorkout

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
        speech = SpeekText(this)
        soundPlayer = SoundPlayerWorkout(this)
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
        with(binding){
            with(tvUpcomingExercise){
                visibility = View.VISIBLE
                text = exerciseList[currentExercisePosition].name
            }
            flRestView.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            tvExerciseName.visibility = View.INVISIBLE
            flExerciseView.visibility = View.INVISIBLE
            ivImage.visibility = View.INVISIBLE
            tvUpcomingExerciseLabel.visibility = View.VISIBLE
            progressBar.progress = restProgress
            restTimer = restTimer.start()
        }
    }

    private fun setupExerciseView() {
        with(binding){
            stopRestTimer()
            with(exerciseList[currentExercisePosition]){
                with(tvExerciseName) {
                    visibility = View.VISIBLE
                    text = name
                }
                with(ivImage) {
                    visibility = View.VISIBLE
                    setImageResource(image)
                }
            }
            flRestView.visibility = View.INVISIBLE
            tvTitle.visibility = View.INVISIBLE
            flExerciseView.visibility = View.VISIBLE
            tvUpcomingExercise.visibility = View.INVISIBLE
            tvUpcomingExerciseLabel.visibility = View.INVISIBLE
            setExerciseProgressBar()
        }
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
                speech.speakOut("Congratulations for finishing the exercise.")
                finish()
            }
        }
    }

    inner class WorkoutTimer(millisInFuture: Long) : CountDownTimer(millisInFuture, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val newProgress = 10 - restProgress
            with(speech) {
                speakOut(
                    if(newProgress == 0) {
                        soundPlayer.start()
                        ""
                    }
                    else "$newProgress"
                )
            }
            binding.progressBar.progress = newProgress
            binding.tvTimer.text = newProgress.toString()
            restProgress++
        }

        override fun onFinish() {
            setupExerciseView()
            currentExercisePosition++
        }
    }

    public override fun onDestroy() {
        soundPlayer.stop()
        speech.stop()
        super.onDestroy()
    }
}