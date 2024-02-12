package com.elmirov.tinkofftesttask.presentation.favorite

import app.cash.turbine.test
import com.elmirov.tinkofftesttask.domain.usecase.GetFilmByIdUseCase
import com.elmirov.tinkofftesttask.navigation.router.FilmInfoRouter
import com.elmirov.tinkofftesttask.presentation.ErrorType
import com.elmirov.tinkofftesttask.presentation.info.FilmInfoState
import com.elmirov.tinkofftesttask.presentation.info.FilmInfoViewModel
import com.elmirov.tinkofftesttask.util.MockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class FilmInfoViewModelTest {

    private val useCase: GetFilmByIdUseCase = mock()
    private val router: FilmInfoRouter = mock()
    private lateinit var viewModel: FilmInfoViewModel

    private val id = MockData.ID

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        viewModel = FilmInfoViewModel(useCase, router)
    }

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        viewModel.state.test {
            assertEquals(FilmInfoState.Initial, awaitItem())
        }
    }

    @Test
    fun `load film success EXPECT Content state`() = runTest {

        whenever(useCase(id)) doSuspendableAnswer {
            delay(1)
            MockData.filmFull
        }

        viewModel.loadFilm(id)

        viewModel.state.test {
            assertEquals(FilmInfoState.Initial, awaitItem())
            assertEquals(FilmInfoState.Loading, awaitItem())
            assertEquals(FilmInfoState.Content(content = MockData.filmFull), awaitItem())
        }
    }

    @Test
    fun `load film error EXPECT Error state`() = runTest {
        whenever(useCase(id)) doSuspendableAnswer {
            delay(1)
            throw Exception()
        }

        viewModel.loadFilm(id)

        viewModel.state.test {
            assertEquals(FilmInfoState.Initial, awaitItem())
            assertEquals(FilmInfoState.Loading, awaitItem())
            assertEquals(FilmInfoState.Error(ErrorType.INTERNET), awaitItem())
        }
    }

    @Test
    fun `back to films EXPECT navigate back`() {
        viewModel.backToFilms()

        verify(router).backToFilms()
    }
}