package ai.chatprism.modlife.modules

import ai.chatprism.modlife.api.Repository
import ai.chatprism.modlife.utils.SharedPrefsHelper
import ai.chatprism.modlife.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Repository(get())
    }
    single {
        SharedPrefsHelper(get())
    }
}
val viewModel= module {
    viewModel {
        LoginViewModel(get())
    }
//     viewModel {
//         DashBoardViewModel(get())
//     }
}