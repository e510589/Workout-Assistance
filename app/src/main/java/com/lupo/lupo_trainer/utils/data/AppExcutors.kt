package com.lupo.lupo_trainer.utils.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExcutors{

    private val diskIO:Executor

    private val mainThread: Executor

    constructor(diskIO:Executor , mainThread:Executor){
        this.diskIO = diskIO
        this.mainThread = mainThread
    }

    fun getDiskIO():Executor{
        return this.diskIO
    }

    fun getMainThread():Executor{
        return this.mainThread
    }

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