package ai.chatprism.modlife.base

import ai.chatprism.modlife.modules.networkModule
import android.app.Application
import ai.chatprism.modlife.modules.appModule
import ai.chatprism.modlife.modules.viewModel

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, appModule, viewModel))

        }
    }

//    companion object {}
}