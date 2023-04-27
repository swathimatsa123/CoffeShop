package com.paypal.coffeeshop

import androidx.navigation.ActivityNavigatorExtras
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun visibilityOfButtons() {
        onView(ViewMatchers.withId(R.id.button_borrow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.button_cancel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.button_increase)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.button_decrease)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun visibilityOfTextFields() {
        onView(ViewMatchers.withId(R.id.name_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.name_text)).perform(ViewActions.typeText("Borrow Books"))
    }

    @Test
    fun visibilityOfCheckBox() {
        onView(ViewMatchers.withId(R.id.fictional_checkbox)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.non_fictional_checkbox)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun borrowFictionalBook() {
        onView(ViewMatchers.withId(R.id.name_text)).perform(ViewActions.typeText("Borrow Books"))
        onView(ViewMatchers.withId(R.id.fictional_checkbox)).perform(click())
        onView(ViewMatchers.withId(R.id.button_increase)).perform(click())
        onView(ViewMatchers.withId(R.id.button_borrow)).perform(click())
    }

    @Test
    fun borrowNonFictionalBook() {
        onView(ViewMatchers.withId(R.id.name_text)).perform(ViewActions.typeText("Borrow Books"))
        onView(ViewMatchers.withId(R.id.non_fictional_checkbox)).perform(click())
        onView(ViewMatchers.withId(R.id.button_increase)).perform(click())
        onView(ViewMatchers.withId(R.id.button_borrow)).perform(click())
    }

    @Test
    fun borrowBothFictionalAndNonFictionalBooks() {
        onView(ViewMatchers.withId(R.id.name_text)).perform(ViewActions.typeText("Borrow Books"))
        onView(ViewMatchers.withId(R.id.fictional_checkbox)).perform(click())
        onView(ViewMatchers.withId(R.id.non_fictional_checkbox)).perform(click())
        onView(ViewMatchers.withId(R.id.button_increase)).perform(click())
        onView(ViewMatchers.withId(R.id.button_increase)).perform(click())
        onView(ViewMatchers.withId(R.id.button_borrow)).perform(click())
    }
}