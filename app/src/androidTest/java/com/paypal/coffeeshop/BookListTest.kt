package com.paypal.coffeeshop

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test

class BookListTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun validateFirstElementInList() {
        Espresso.onView(ViewMatchers.withId(R.id.listView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onData(
            AllOf.allOf(
                CoreMatchers.`is`(Matchers.instanceOf(String::class.java)),
                CoreMatchers.startsWith("Apple")
            )
        ).perform(ViewActions.click())
        Espresso.onData(
            AllOf.allOf(
                CoreMatchers.`is`(Matchers.instanceOf(String::class.java)),
                CoreMatchers.startsWith("Apple")
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("Apple")))
    }

    @Test
    fun validateLastElementInList() {
        Espresso.onData(
            AllOf.allOf(
                CoreMatchers.`is`(Matchers.instanceOf(String::class.java)),
                CoreMatchers.endsWith("Watermelon")
            )
        ).perform(ViewActions.click())
    }

    @Test
    fun validateElementWithText() {
        Espresso.onData(
            AllOf.allOf(
                CoreMatchers.`is`(Matchers.instanceOf(String::class.java)),
                CoreMatchers.containsString("Mango")
            )
        ).perform(ViewActions.click())
    }

    @Test
    fun validateElementWithPosition() {
        Espresso.onData(AllOf.allOf())
            .inAdapterView(ViewMatchers.withId(R.id.listView))
            .atPosition(18)
            .perform(ViewActions.click())
    }
}