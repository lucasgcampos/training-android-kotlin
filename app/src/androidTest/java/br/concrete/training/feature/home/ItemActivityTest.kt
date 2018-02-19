package br.concrete.training.feature.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import br.concrete.training.R
import br.concrete.training.feature.item.ItemActivity
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.contrib.ActivityResultMatchers.hasResultCode
import android.support.test.espresso.contrib.ActivityResultMatchers.hasResultData
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers


/**
 * Created by eliete on 2/16/18.
 */
class ItemActivityTest {

    companion object {
        val DEFAULT_EMPTY_DESCRIPTION = "-"
        val DIALOG_TITLE = "Atenção"
        val TITLE = "título"
        val DESCRIPTION = "Alguma descrição relevante ao tópico"
        val EMPTY_STRING = ""
    }

    @Rule
    @JvmField
    var activityRule = IntentsTestRule<ItemActivity>(
            ItemActivity::class.java, true, false)


    @Test
    fun whenTryToCreateAnEmptyTask_shouldShowDialog() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.task)).perform(replaceText(EMPTY_STRING))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        Thread.sleep(500)
        onView(withId(R.id.add_item)).perform(click())

        onView(withText(DIALOG_TITLE)).check(matches(isDisplayed()))
    }

    @Test
    fun whenClickToCreateTask_shouldHomeIntentFired() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        Thread.sleep(500)
        onView(withId(R.id.add_item)).perform(click())

        assertThat(activityRule.activityResult, hasResultCode(Activity.RESULT_OK))
        assertThat(activityRule.activityResult, hasResultData(IntentMatchers.hasExtraWithKey(HomeActivity.ITEM_EXTRAS)))
    }
}