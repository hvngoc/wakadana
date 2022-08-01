package com.waka.dana.na

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.waka.dana.na.data.di.cacheModule
import com.waka.dana.na.data.di.networkModule
import com.waka.dana.na.domain.di.useCaseModule
import com.waka.dana.na.domain.repository.WeatherRepository
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.presentation.di.appModule
import com.waka.dana.na.presentation.screen.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

/**
 * Created by hvngoc on 8/1/22
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ViewModelTest : KoinTest {

    private val mainViewModel: MainViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        val mockRepo = mock(WeatherRepository::class.java)

        val mockModule = module {
            single<WeatherRepository> { mockRepo }
        }

        stopKoin()
        startKoin {
            modules(appModule, networkModule, useCaseModule, cacheModule, mockModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun demoTest() {
        runBlocking {
            mainViewModel.loadData("Fake")
        }
    }
}