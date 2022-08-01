package com.waka.dana.na

import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.waka.dana.na.domain.error.MasterThrowable
import com.waka.dana.na.domain.model.WeatherList
import com.waka.dana.na.domain.repository.WeatherRepository
import com.waka.dana.na.domain.response.CommonError
import com.waka.dana.na.presentation.screen.MainActivity
import com.waka.dana.na.presentation.screen.MainFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.concurrent.TimeUnit

/**
 * Created by hvngoc on 8/1/22
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeTest : KoinTest {

    @get:Rule
    var intentRule = IntentsTestRule(MainActivity::class.java, true, false)

    lateinit var mockModule: Module
    lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = Mockito.mock(WeatherRepository::class.java)
        mockModule = module {
            single<WeatherRepository> { repository }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun testApiError() {
        `when`(repository.getWeather("saigon")).thenThrow(
            MasterThrowable(
                error = CommonError(
                    "199",
                    "City not found"
                )
            )
        )
        intentRule.launchActivity(null)
        intentRule.activity?.runOnUiThread {
            startFragment()
        }

        onView(withId(R.id.edit_text)).perform(clearText(), typeText("saigon"))
        Thread.sleep(1000)

        onView(withId(R.id.error))
            .check(matches(withText("City not found")))
    }

    private fun mockData(): WeatherList {
        return WeatherList(city = null, cod = "399", message = 33.3, cnt = 3, list = emptyList())
    }

    private fun startFragment(): MainFragment? {
        val activity: MainActivity = intentRule.activity as MainActivity
        val transaction: FragmentTransaction =
            activity.supportFragmentManager.beginTransaction()
        val voiceFragment = MainFragment()
        transaction.add(voiceFragment, MainFragment.TAG)
        transaction.commit()
        return voiceFragment
    }
}