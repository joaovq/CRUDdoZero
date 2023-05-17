package com.queapps.cruddozero.ui.subscriberlist

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.queapps.cruddozero.MainActivity
import com.queapps.cruddozero.R
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SubscriberListFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scrollList() {
        onView(
            withId(com.queapps.cruddozero.R.id.recycler_subscribes),
        ).check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<SubscriberListAdapter.SubscriberListViewHolder>(20),
            )
    }

    @Test
    fun clickItem() {
        val fragmentScenario = launchFragmentInContainer<SubscriberListFragment>(themeResId = R.style.Theme_CRUDdoZero)
        val navHostController =
            TestNavHostController(ApplicationProvider.getApplicationContext())
        fragmentScenario.onFragment {
            navHostController.setGraph(com.queapps.cruddozero.R.navigation.main_graph)
            Navigation.setViewNavController(it.requireView(), navHostController)
        }

        onView(
            withId(R.id.recycler_subscribes),
        ).check(matches(isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<SubscriberListAdapter.SubscriberListViewHolder>(
                0,
                click(),
            ),
        )
        assertEquals(navHostController.currentDestination?.id, R.id.subscriberFragment)
    }
}
