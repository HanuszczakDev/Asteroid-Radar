package com.hanuszczak.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.model.repository.ApiRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = Db.getInstance(applicationContext)
        val repository = ApiRepository(database)
        return try {
            repository.getPictureFromApi()
            repository.getAsteroidsFromApi()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}