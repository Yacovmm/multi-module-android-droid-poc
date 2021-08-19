package libraries.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import libraries.core.coroutines.ICoroutinesDispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named("RealOkHttp")
    fun provideOkHttpClient(
        @Named("LoggingInterceptor") loggingInterceptor: Interceptor,
        @Named("HeadersInterceptor") headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("RealOkHttp") client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.daycoval.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    @Named("MockOkHttp")
    fun provideMockOkHttpClient(
        @Named("LoggingInterceptor") loggingInterceptor: Interceptor,
        @Named("HeadersInterceptor") headerInterceptor: Interceptor,
        @Named("MockInterceptor") mockInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("MockRetrofit")
    fun provideMockRetrofit(
        @Named("MockOkHttp") client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.daycoval.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
            .registerTypeAdapterFactory(GsonNullableTypeAdapter())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitWrapper(dispatchers: ICoroutinesDispatchers): IRetrofitWrapper {
        return RetrofitWrapperImpl(dispatchers = dispatchers)
    }
}
