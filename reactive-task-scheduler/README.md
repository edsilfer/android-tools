# Task Scheduler
[ ![Download](https://api.bintray.com/packages/edsilfer/maven/reactive-scheduler/images/download.svg) ](https://bintray.com/edsilfer/maven/reactive-scheduler/_latestVersion)

## Purpose
This library provides an easy way to schedule tasks to be performed sequentially in a reactive pipe, allowing client to check execution logs as well as deciding which of them should interrupt the pipe.

## How to use it?
1. Add library dependency:

```groovy
dependencies {
    compile 'br.com.edsilfer.android:reactive-task-scheduler:+'
}
```

2. Implement the ```Task``` interface. Example:

```kotlin
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
```
3. Instantiate TaskScheduler and register the taks:

```kotlin
 private val scheduler: Scheduler = SchedulerImpl()
 
 ...
 
 scheduler.register(SampleBackgroundTask("Sample Background Task 1", time = 800))
 scheduler.register(SampleBackgroundTask("Sample Background Task 2", time = 500))
 scheduler.register(SampleBackgroundTask("Sample Background Task 3", time = 250, blockOnError = true))
 scheduler.register(SampleBackgroundTask("Sample Background Task 4", time = 2000))
 
 ...
 
```

4. Execute schedule tasks:

```kotlin
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
```

# License
Copyright 2017 Edgar da Silva Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
