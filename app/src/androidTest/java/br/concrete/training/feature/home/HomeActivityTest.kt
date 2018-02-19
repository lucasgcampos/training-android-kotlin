package br.concrete.training.feature.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import br.concrete.training.R
import br.concrete.training.data.model.Item2
import br.concrete.training.feature.item.ItemActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by eliete on 2/16/18.
 */
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = IntentsTestRule(HomeActivity::class.java, false, false)

    @Test
    fun whenClickToCreateTask_shouldItemIntentIsFired() {
        activityRule.launchActivity(Intent())

        intending(IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName(ItemActivity::class.java.name)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))

        onView(withId(R.id.add_item)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName(ItemActivity::class.java.name)))

    }

    @Test
    fun whenCompleteTaskCreated_shouldShowExpectedTaskWithSuccess() {

        activityRule.launchActivity(Intent())

        val intent = Intent()
        intent.putExtra(HomeActivity.ITEM_EXTRAS , Item2(ItemActivityTest.TITLE, ItemActivityTest.DESCRIPTION))
        montaIntentASerLancada(intent)

        montaIntentASerLancada(intent)

        onView(withId(R.id.add_item)).perform(click())
        onView(ViewMatchers.withText(ItemActivityTest.TITLE))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText(ItemActivityTest.DESCRIPTION))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun whenATaskCreated_withNoDescription_shouldShowDescriptionTaskWithHifen() {
        activityRule.launchActivity(Intent())

        val intent = Intent()
        intent.putExtra(HomeActivity.ITEM_EXTRAS , Item2(ItemActivityTest.TITLE,
                ItemActivityTest.DEFAULT_EMPTY_DESCRIPTION))
        montaIntentASerLancada(intent)
        onView(withId(R.id.add_item)).perform(click())
        onView(ViewMatchers.withText(ItemActivityTest.TITLE))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText(ItemActivityTest.DEFAULT_EMPTY_DESCRIPTION))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun montaIntentASerLancada(intent: Intent) {
        intending(IntentMatchers.hasComponent(ItemActivity::class.java.name)).respondWith(
                Instrumentation.ActivityResult(Activity.RESULT_OK, intent))
    }


}