package com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRetrofit(context: Context, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkhttpClient(context))
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(provideChuckerInterceptor(context))
        .build()
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

private fun provideChuckerInterceptor(context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context).build()
}

fun provideReqresService(context: Context): ReqresService {
    return provideRetrofit(
        context,
        "https://reqres.in/api/"
    ).create(ReqresService::class.java)
}

fun providePlayerService(context: Context): ApiPlayerService {
    return provideRetrofit(
        context,
        "https://ligaindonesia-api.vercel.app/",
    ).create(ApiPlayerService::class.java)
}

