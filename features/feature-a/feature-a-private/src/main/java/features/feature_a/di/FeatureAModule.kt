package features.feature_a.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import features.feature_a.BuildConfig
import features.feature_a.domain.FeatureARepositoryImpl
import features.feature_a_public.data.network.FeatureAService
import features.feature_a_public.data.repositort.IFeatureARepository
import libraries.core.network.IRetrofitWrapper
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureAModule {

    @Singleton
    @Provides
    fun provideFeatureAService(
        retrofitReal: Retrofit,
        @Named("MockRetrofit") retrofitMock: Retrofit
    ): FeatureAService {
        if (BuildConfig.MOCK_WEBSERVER) {
            return retrofitMock.create(FeatureAService::class.java)
        }
        return retrofitReal.create(FeatureAService::class.java)
    }

    @Provides
    @Singleton
    fun provideFeatureARepository(
        retrofitWrapperImpl: IRetrofitWrapper,
        service: FeatureAService
    ): IFeatureARepository = FeatureARepositoryImpl(retrofitWrapperImpl, service)
}
