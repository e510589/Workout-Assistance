package com.lupo.lupo_trainer.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExcutors(val diskIO:Executor = DiskIOThreadExecutor(),val mainThread:Executor = MainThreadExecutor()){


    companion object{

        class MainThreadExecutor : Executor{
            val mainThreadHandler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable?) {
                if (command != null)
                    mainThreadHandler.post(command)
            }
        }

        class DiskIOThreadExecutor : Executor{

            private val mDiskIO:Executor = Executors.newSingleThreadExecutor()

            override fun execute(command: Runnable?) {
                if (command != null){
                    mDiskIO.execute(command)
                }
            }
        }
    }
}