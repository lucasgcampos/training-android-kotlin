package br.concrete.training.feature.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import br.concrete.training.R
import br.concrete.training.feature.item.ItemActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by eliete on 2/16/18.
 */
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = IntentsTestRule(HomeActivity::class.java, true, false)

    @Test
    fun whenClickToCreateTask_shouldItemIntentWasFired() {
        activityRule.launchActivity(Intent())

        Intents.intending(IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName(ItemActivity::class.java.name)))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))

        onView(withId(R.id.add_item)).perform(click())

        Intents.intended(IntentMatchers.hasComponent(
                ComponentNameMatchers.hasClassName(ItemActivity::class.java.name)))

    }
}