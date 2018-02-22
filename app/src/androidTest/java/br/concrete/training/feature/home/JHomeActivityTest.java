package br.concrete.training.feature.home;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.concrete.training.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Lucas Campos
 */
public class JHomeActivityTest {

    private static final String DEFAULT_EMPTY_DESCRIPTION = "-";
    private static final String DIALOG_TITLE = "Atenção";
    private static final String TITLE = "título";
    private static final String DESCRIPTION = "Alguma descrição relevante ao tópico";
    private static final String EMPTY_STRING = "";

    @Rule
    public ActivityTestRule<JHomeActivity> activityRule = new ActivityTestRule<>(JHomeActivity.class, true, false);

    @Test
    public void shouldCreateItemCorrectly() {
        // given
        activityRule.launchActivity(new Intent());

        // when
        onView(withId(R.id.add_item)).perform(click());
        onView(withId(R.id.task_item)).perform(replaceText(TITLE));
        onView(withId(R.id.description_item)).perform(replaceText(DESCRIPTION));
        onView(withId(R.id.add_item)).perform(click());

        // then
        onView(withText(TITLE)).check(matches(isDisplayed()));
        onView(withText(DESCRIPTION)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldTryCreateItemWithEmptyTask() throws InterruptedException {
        // given
        activityRule.launchActivity(new Intent());

        // when
        onView(withId(R.id.add_item)).perform(click());
        onView(withId(R.id.task_item)).perform(replaceText(EMPTY_STRING));
        onView(withId(R.id.description_item)).perform(replaceText(DESCRIPTION));
        onView(withId(R.id.add_item)).perform(click());

        // then
        onView(withText(DIALOG_TITLE)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldTryCreateItemWithEmptyDescription() {
        // given
        activityRule.launchActivity(new Intent());

        // when
        onView(withId(R.id.add_item)).perform(click());
        onView(withId(R.id.task_item)).perform(replaceText(TITLE));
        onView(withId(R.id.description_item)).perform(replaceText(EMPTY_STRING));
        onView(withId(R.id.add_item)).perform(click());

        // then
        onView(withText(TITLE)).check(matches(isDisplayed()));
        onView(withText(DEFAULT_EMPTY_DESCRIPTION)).check(matches(isDisplayed()));
    }

}
