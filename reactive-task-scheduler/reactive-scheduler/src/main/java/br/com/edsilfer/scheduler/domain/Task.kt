package br.com.edsilfer.scheduler.domain

import io.reactivex.Completable


interface Task {

    fun run(): Completable

    fun getDescription(): String

    fun blockOnError(): Boolean

}