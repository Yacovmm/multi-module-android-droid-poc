package features.feature_a.presentation.FirstScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import features.auth.auth_public.domain.entities.UrlEntity
import features.feature_a_public.data.models.TesteResponse
import features.feature_a_public.data.repositort.IFeatureARepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val featureARepository: IFeatureARepository
) : ViewModel() {

    var urlsList: List<UrlEntity> = emptyList()

    private val _testeLiveData = MutableLiveData<TesteResponse>()
    val testeLiveData: LiveData<TesteResponse>
        get() = _testeLiveData

    fun getData() = viewModelScope.launch {
        val response = featureARepository.getTeste()

        _testeLiveData.postValue(response)
    }
}
