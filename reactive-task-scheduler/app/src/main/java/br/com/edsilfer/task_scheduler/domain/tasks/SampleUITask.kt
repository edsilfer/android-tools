package br.com.edsilfer.task_scheduler.domain.tasks

import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.scheduler.domain.Task
import br.com.edsilfer.task_scheduler.R
import br.com.edsilfer.task_scheduler.presentation.DummyView
import io.reactivex.Completable
import rx_activity_result2.RxActivityResult

class SampleUITask(val context: AppCompatActivity) : Task {

    override fun run(): Completable {
        return Completable.fromObservable(RxActivityResult.on(context).startIntent(DummyView.getIntent(context)))
    }

    override fun getDescription(): String {
        return context.getString(R.string.str_sample_ui_task_description)
    }

    override fun blockOnError(): Boolean {
        return false
    }

}