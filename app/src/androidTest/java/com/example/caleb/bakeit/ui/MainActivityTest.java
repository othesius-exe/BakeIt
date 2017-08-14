package com.example.caleb.bakeit.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.caleb.bakeit.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {



    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void scrollThroughRecyclerView() {
        Espresso.onView(withId(R.id.card_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(3));
    }

    @Test
    public void onClickRecyclerView_OpensDirectionsActivity() {
        Espresso.onView(withId(R.id.card_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Espresso.onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        Espresso.onView(allOf(isDisplayed(), withId(R.id.exo_player)));
        Espresso.onView(withId(R.id.exo_play)).check(matches(isEnabled()));
    }
}