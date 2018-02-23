package br.concrete.training.feature.home

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.concrete.training.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Lucas Campos
 */
@RunWith(AndroidJUnit4::class)
class JHomeActivityTest {

    @Rule
    var activityRule = ActivityTestRule(JHomeActivity::class.java, true, false)

    @Test
    fun shouldCreateItemCorrectly() {
        // given
        activityRule.launchActivity(Intent())

        // when
        onView(withId(R.id.add_item)).perform(click())
        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        onView(withId(R.id.add_item)).perform(click())

        // then
        onView(withText(TITLE)).check(matches(isDisplayed()))
        onView(withText(DESCRIPTION)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldTryCreateItemWithEmptyTask() {
        // given
        activityRule.launchActivity(Intent())

        // when
        onView(withId(R.id.add_item)).perform(click())
        onView(withId(R.id.task)).perform(replaceText(EMPTY_STRING))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        onView(withId(R.id.add_item)).perform(click())

        // then
        onView(withText(DIALOG_TITLE)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldTryCreateItemWithEmptyDescription() {
        // given
        activityRule.launchActivity(Intent())

        // when
        onView(withId(R.id.add_item)).perform(click())
        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(EMPTY_STRING))
        onView(withId(R.id.add_item)).perform(click())

        // then
        onView(withText(TITLE)).check(matches(isDisplayed()))
        onView(withText(DEFAULT_EMPTY_DESCRIPTION)).check(matches(isDisplayed()))
    }

    companion object {
        const val DEFAULT_EMPTY_DESCRIPTION = "-"
        const val DIALOG_TITLE = "Atenção"
        const val TITLE = "título"
        const val DESCRIPTION = "Alguma descrição relevante ao tópico"
        const val EMPTY_STRING = ""
    }

}
