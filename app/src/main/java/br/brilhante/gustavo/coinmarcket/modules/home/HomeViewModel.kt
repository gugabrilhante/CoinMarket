package br.brilhante.gustavo.coinmarcket.modules.home

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.brilhante.gustavo.coinmarcket.database.coin.CoinRepository
import br.brilhante.gustavo.coinmarcket.dataproviders.CoinRequestManager
import br.brilhante.gustavo.coinmarcket.models.Coin
import br.brilhante.gustavo.coinmarcket.service.DownloadCoinJobService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    var coinLiveData: LiveData<List<Coin>>? = CoinRepository.getCoinLiveData()

    companion object {
        private const val JOB_ID = 1
    }

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun startList(context: Context) {
        getCoins(context)
    }

    private fun getCoins(context: Context) {
        compositeDisposable.add(
            CoinRequestManager.getCoinList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    if(it.isNotEmpty())CoinRepository.insertCoins(it)
                    scheduleDownloadCoinJobService(context)
                }, {
                    // TODO treat error(show any UI message)
                    scheduleDownloadCoinJobService(context)
                })
        )
    }

    private fun scheduleDownloadCoinJobService(context: Context) {
        val jobScheduler = context.applicationContext.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(context, DownloadCoinJobService::class.java)
        val jobInfo: JobInfo
        jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setPeriodic(15 * 60 * 1000)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()
        jobScheduler.cancelAll()
        jobScheduler.schedule(jobInfo)

    }

}