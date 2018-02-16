package br.concrete.training.feature.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import br.concrete.training.R
import br.concrete.training.data.model.Item
import br.concrete.training.feature.item.ItemActivity
import org.junit.Rule
import org.junit.Test

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
    fun whenCreateCompleteTask_shouldShowExpectedTaskWithSuccess() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        Thread.sleep(800)
        onView(withId(R.id.add_item)).perform(click())

        onView(withText(TITLE)).check(matches(isDisplayed()))
        onView(withText(DESCRIPTION)).check(matches(isDisplayed()))
    }

    @Test
    fun whenCreateATask_withNoDescription_shouldShowDescriptionTaskWithHifen() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(EMPTY_STRING))
        Thread.sleep(800)
        onView(withId(R.id.add_item)).perform(click())

        onView(withText(TITLE)).check(matches(isDisplayed()))
        onView(withText(DEFAULT_EMPTY_DESCRIPTION)).check(matches(isDisplayed()))
    }

    @Test
    fun whenTryToCreateAnEmptyTask_shouldShowDialog() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.task)).perform(replaceText(EMPTY_STRING))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))
        Thread.sleep(800)
        onView(withId(R.id.add_item)).perform(click())

        onView(withText(DIALOG_TITLE)).check(matches(isDisplayed()))
    }

    @Test
    fun whenClickToCreateTask_shouldHomeIntentWasFired() {
        var intent = Intent()
        intent.putExtra(HomeActivity.ITEM_EXTRAS, Item(TITLE, DESCRIPTION))

        activityRule.launchActivity(Intent())

        Intents.intending(IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName(HomeActivity::class.java.name)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, intent))

        onView(withId(R.id.task)).perform(replaceText(TITLE))
        onView(withId(R.id.description)).perform(replaceText(DESCRIPTION))

        Thread.sleep(800)
        onView(withId(R.id.add_item)).perform(click())

        onView(withText(TITLE)).check(matches(isDisplayed()))
        onView(withText(DESCRIPTION)).check(matches(isDisplayed()))

    }
}