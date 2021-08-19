package com.example.auth_private.di

import com.example.auth_private.BuildConfig
import com.example.auth_private.domain.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import features.auth.auth_public.data.network.AuthService
import features.auth.auth_public.data.repository.IAuthRepository
import libraries.core.network.IRetrofitWrapper
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Singleton
    @Provides
    fun provideAuthService(
        retrofitReal: Retrofit,
        @Named("MockRetrofit") retrofitMock: Retrofit
    ): AuthService {
        if (BuildConfig.MOCK_WEBSERVER) {
            return retrofitMock.create(AuthService::class.java)
        }
        return retrofitReal.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        retrofitWrapperImpl: IRetrofitWrapper,
        service: AuthService
    ): IAuthRepository = AuthRepositoryImpl(retrofitWrapperImpl, service)
}
