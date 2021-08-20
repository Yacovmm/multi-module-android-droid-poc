package libraries.core.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import libraries.core.network.mock.ApiMockManager
import libraries.core.network.mock.ResourceReader
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorsModule {

    @Singleton
    @Provides
    @Named("LoggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    @Named("HeadersInterceptor")
    fun provideHeadersInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    @Named("MockInterceptor")
    fun provideMockInterceptor(
        @ApplicationContext context: Context
    ): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.toUrl().path
            val jsonRaw = ApiMockManager.checkUrlAndGetJson(url)

            val jsonInputStream: InputStream = context.resources.openRawResource(jsonRaw)

            val json = ResourceReader.readFileWithoutNewLineFromResources(jsonInputStream)
            val response = Response.Builder()
                .code(200)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .addHeader("content-type", "application/json")
                .body(json.toResponseBody("application/json".toMediaType()))
                .message("Nothing")
                .build()
            runBlocking { delay(500) }
            response
        }
    }
}
