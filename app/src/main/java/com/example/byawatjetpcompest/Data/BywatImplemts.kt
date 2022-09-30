package com.plcoding.jetpackcomposepokedex.di

import com.example.byawatjetpcompest.Data.BywatMathod
import com.example.byawatjetpcompest.Pojo.DitealsScreen.DetialsRoot
import com.example.byawatjetpcompest.Pojo.HomePage.HomeRoot
import com.example.byawatjetpcompest.Pojo.global.globalsRoot
import com.google.android.gms.common.api.Response
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BywatImplemts {
    private val baseUrl = "https://dejavu360vr.com/api/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().client(
            providesOkHttpClient()).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {

        return   OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val builder: Request.Builder = chain.request().newBuilder()
                //  builder.addHeader("Authorization", "Bearer " + Constans.Token)
                builder.addHeader("Content-Type", "multipart/form-data")
                val request: Request = builder.build()
                val response: okhttp3.Response = chain.proceed(request)
                response
            }
            .followRedirects(false).build()
    }

    @Provides
    @Singleton
    fun ProvideConnectServies(retrofit: Retrofit):BywatMathod{
        return retrofit.create(BywatMathod::class.java)
    }


}