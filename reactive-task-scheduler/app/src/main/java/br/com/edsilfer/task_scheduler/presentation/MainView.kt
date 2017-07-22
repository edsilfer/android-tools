package br.com.edsilfer.task_scheduler.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.scheduler.domain.Scheduler
import br.com.edsilfer.scheduler.domain.SchedulerImpl
import br.com.edsilfer.task_scheduler.R
import br.com.edsilfer.task_scheduler.domain.tasks.SampleBackgroundTask
import br.com.edsilfer.task_scheduler.domain.tasks.SampleUITask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class MainView : AppCompatActivity() {

    private val scheduler: Scheduler = SchedulerImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        scheduler.register(SampleBackgroundTask("Sample Background Task 1", time = 800))
        scheduler.register(SampleBackgroundTask("Sample Background Task 2", time = 500))
        scheduler.register(SampleBackgroundTask("Sample Background Task 3", time = 250, blockOnError = true))
        scheduler.register(SampleBackgroundTask("Sample Background Task 4", time = 2000))
        scheduler.register(SampleUITask(this))
        scheduler.register(SampleBackgroundTask("Sample Background Task 5", time = 800, blockOnError = false))
        scheduler.register(SampleBackgroundTask("Sample Background Task 6", time = 200))

        val start = Date().time
        scheduler
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSchedulerFinishedSuccessfully(start) },
                        { error -> onSchedulerFinishedUnsuccessfully(error) }
                )
    }

    private fun onSchedulerFinishedUnsuccessfully(error: Throwable) {
        String.format(getString(R.string.str_scheduler_finished_successfully), error.message)
    }

    private fun onSchedulerFinishedSuccessfully(start: Long) {
        Timber.i(String.format(getString(R.string.str_scheduler_finished_successfully), Date().time - start))
    }
}

