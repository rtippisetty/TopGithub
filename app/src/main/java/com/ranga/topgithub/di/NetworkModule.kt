package com.ranga.topgithub.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ranga.topgithub.BuildConfig
import com.ranga.topgithub.R
import com.ranga.topgithub.common.BASE_URL
import com.ranga.topgithub.common.TIME_OUT_SEC
import com.ranga.topgithub.data.network.GitReposService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    /**
     * Glide dependencies
     *
     */
    @JvmStatic
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)


    @JvmStatic
    @Singleton
    @Provides
    fun provideRequestManager(applicationContext: Context, requestOptions: RequestOptions)
            : RequestManager = Glide.with(applicationContext).setDefaultRequestOptions(requestOptions)


    /**
     * Retrofit dependencies
     */

    @JvmStatic
    @Singleton
    @Provides
    fun provideGitRepoService(retrofit: Retrofit): GitReposService {
        return retrofit.create(GitReposService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient):Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().newBuilder()
        .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}