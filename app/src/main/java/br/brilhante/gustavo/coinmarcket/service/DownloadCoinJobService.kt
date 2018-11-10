package br.brilhante.gustavo.coinmarcket.service

import android.app.job.JobParameters
import android.app.job.JobService
import br.brilhante.gustavo.coinmarcket.database.coin.CoinRepository
import br.brilhante.gustavo.coinmarcket.dataproviders.CoinRequestManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DownloadCoinJobService : JobService() {

    companion object {
        var compositeDisposable: CompositeDisposable = CompositeDisposable()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        compositeDisposable.clear()
        compositeDisposable.add(
            CoinRequestManager.getCoinList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    if(it.isNotEmpty()) {
                        CoinRepository.insertCoins(it)
                    }else{
                        // TODO treat error(or just wait next onStartJob)
                    }
                },{
                    // TODO treat error
                })
        )
        return true
    }
}