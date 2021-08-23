package features.feature_a.presentation.FirstScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import features.auth.auth_public.domain.entities.UrlEntity
import features.feature_a_public.data.repositort.IFeatureARepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import libraries.test_utils.utils.MainCoroutineRule
import libraries.test_utils.utils.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FirstScreenViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: FirstScreenViewModel

    lateinit var repository: IFeatureARepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = FirstScreenViewModel(repository)

        viewModel.urlsList = listOf(
            UrlEntity(id = 1, "pix", "pix.com"),
            UrlEntity(id = 1, "openBanking", "openBanking.com"),
            UrlEntity(id = 1, "auth", "auth.com"),
            UrlEntity(id = 1, "veiculo", "pix.com"),
            UrlEntity(id = 1, "credito", "credito.com"),
        )
    }

    @Test
    fun `order items by A-Z, index 1, pokemons should be ordered`() {
        viewModel.orderBySpinner(1)
        val items = viewModel.filteredLiveData.getOrAwaitValueTest()

        assertThat(items.first().name).isEqualTo("auth")
        assertThat(items.last().name).isEqualTo("veiculo")
    }

    @Test
    fun `order items by nothing, index 0 should return the normal list`() {
        viewModel.orderBySpinner(0)
        val items = viewModel.filteredLiveData.getOrAwaitValueTest()

        assertThat(items.first().name).isEqualTo("pix")
        assertThat(items.last().name).isEqualTo("credito")
    }

    @Test
    fun `order items Z-A, index 2, should return the reversed list`() {
        viewModel.orderBySpinner(2)
        val items = viewModel.filteredLiveData.getOrAwaitValueTest()

        assertThat(items.first().name).isEqualTo("veiculo")
        assertThat(items.last().name).isEqualTo("auth")
    }
}
