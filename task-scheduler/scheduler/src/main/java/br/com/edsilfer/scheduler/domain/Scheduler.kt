package br.com.edsilfer.scheduler.domain

import io.reactivex.Completable


interface Scheduler {

    fun register(task: Task): Boolean

    fun unregister(task: Task): Boolean

    fun execute(): Completable

    fun clear()

}