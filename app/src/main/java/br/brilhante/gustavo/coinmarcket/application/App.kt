package br.brilhante.gustavo.coinmarcket.application

import android.app.Application
import br.brilhante.gustavo.coinmarcket.database.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getAppDataBase(this)
    }
}