package br.com.edsilfer.scheduler.domain

import android.util.Log
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import java.util.*


class SchedulerImpl : Scheduler {

    private val tasks: MutableList<Task> = ArrayList()
    private var currentTask = 0

    override fun unregister(task: Task): Boolean {
        return tasks.remove(task)
    }

    override fun register(task: Task): Boolean {
        if (tasks.contains(task)) {
            return false
        }
        return tasks.add(task)
    }

    override fun clear() {
        tasks.clear()
    }

    override fun execute(): Completable {
        return Completable.create {
            emmiter ->
            executeTask(emmiter)
        }
    }

    private fun executeTask(emmiter: CompletableEmitter) {
        if (hasProcessedAllTasks()) {
            emmiter.onComplete()
            return
        }

        val task = tasks[currentTask]
        val startTime = Date().time

        task.run().subscribe(
                { onTaskFinishedSuccessfully(task, startTime, emmiter) },
                { error -> onTaskFinishedWithError(task, error, startTime, emmiter) }
        )
    }

    private fun onTaskFinishedWithError(task: Task, error: Throwable, startTime: Long, emmiter: CompletableEmitter) {
        currentTask++
        logTaskExecuteUnsuccessfully(task, error, startTime)

        if (task.blockOnError()) {
            emmiter.onError(error)
            return
        }

        executeTask(emmiter)
    }

    private fun onTaskFinishedSuccessfully(task: Task, startTime: Long, emmiter: CompletableEmitter) {
        currentTask++
        logTaskExecuteSuccessfully(task, startTime)
        executeTask(emmiter)
    }

    private fun hasProcessedAllTasks(): Boolean {
        return tasks.isEmpty() || currentTask > tasks.size - 1
    }

    private fun logTaskExecuteSuccessfully(previous: Task, startTime: Long) {
        Log.i(SchedulerImpl::class.simpleName, "Finished execution of ${previous.getDescription()} in ${Date().time - startTime} milliseconds.")
    }

    private fun logTaskExecuteUnsuccessfully(previous: Task, error: Throwable, startTime: Long) {
        Log.e(SchedulerImpl::class.simpleName, "Execution of ${previous.getDescription()} finished with error in ${Date().time - startTime} milliseconds. Error Message: $error")
    }

}