package ai.chatprism.modlife.modules

import ai.chatprism.modlife.api.ApiCallInterface
import ai.chatprism.modlife.api.Urls
import ai.chatprism.modlife.utils.Constant
import android.content.Context
import android.content.SharedPreferences
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideRetrofitInterface(get()) }
    single { provideRetrofit(get()) }
    single { provideSharedPrefs(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    return OkHttpClient.Builder()
        .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
//            request.addHeader("Authorization", AppConstants.Bearer + " " + AppConstants.AUTH_TOKEN)
            request.addHeader("Accept", "application/json")
            chain.proceed(request.build())
        }
        .connectTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .cache(null)
        .build()

}

fun provideRetrofitInterface(retrofit: Retrofit): ApiCallInterface = retrofit.create(
    ApiCallInterface::class.java)

fun provideSharedPrefs(appContext: Context): SharedPreferences {
    return appContext.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE)
}

