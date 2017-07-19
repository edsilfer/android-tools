package br.com.edsilfer.task_scheduler.domain.tasks

import br.com.edsilfer.scheduler.domain.Task
import io.reactivex.Completable

open class SampleBackgroundTask(
        val desc: String,
        val blockOnError: Boolean = false,
        val time: Long = ARG_DEFAULT_SAMPLE_TASK_PROCESSING_TIME
) : Task {

    companion object {
        private val ARG_DEFAULT_SAMPLE_TASK_PROCESSING_TIME = 1000L
    }

    override fun run(): Completable {
        return Completable.create {
            emmiter ->
            run {
                Thread().run {
                    Thread.sleep(time)
                    if (blockOnError) {
                        emmiter.onError(IllegalStateException("BackgroundTask finished with error."))
                    }
                    emmiter.onComplete()
                }
            }
        }
    }

    override fun blockOnError(): Boolean {
        return blockOnError
    }

    override fun getDescription(): String {
        return desc
    }

}