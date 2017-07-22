package br.com.edsilfer.task_scheduler.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.task_scheduler.R


class DummyView : AppCompatActivity() {

    companion object {
        private val ARG_VIEW_LIFETIME = 3000L

        fun getIntent(context: Context): Intent {
            return Intent(context, DummyView::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dummy_view)

        Thread(Runnable {
            Thread.sleep(ARG_VIEW_LIFETIME)
            this@DummyView.finish()
        }).start()
    }

}