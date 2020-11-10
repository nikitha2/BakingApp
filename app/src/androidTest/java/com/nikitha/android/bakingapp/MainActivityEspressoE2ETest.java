package com.nikitha.android.bakingapp;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoE2ETest {
    private final String LOG= MainActivityEspressoE2ETest.class.getSimpleName();
    private static final int ITEM_BELOW_THE_FOLD = 4;
    private String brownieStep_3= "3.\tAdd sugars to wet mixture.";
    private String cheesecakeStep_6= "6.\tAdd eggs.";

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule= new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void clickBrownies_enterRecipieAndDetailsActivityForBrownie() {
        onView(allOf(withText("Brownies"))).perform(click());

        onView(ViewMatchers.withId(R.id.recipie_frgament))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(brownieStep_3))));

        onView(ViewMatchers.withText(brownieStep_3))
                .perform(click());

        // Match the text in an item below the fold and check that it's displayed.
        String brownieStep3 = getApplicationContext().getResources().getString(R.string.brownie3) ;
        onView(withText(brownieStep3)).check(matches(isDisplayed()));
    }

    @Test
    public void clickCheeseCake_enterRecipieAndDetailsActivityForCheeseCake() {
        onView(allOf(withText("Cheesecake"))).perform(click());

        onView(ViewMatchers.withId(R.id.recipie_frgament)).perform(RecyclerViewActions.scrollTo(
                hasDescendant(withText(cheesecakeStep_6))));

        onView(ViewMatchers.withText(cheesecakeStep_6))
                .perform(click());

        String cheesecakeStep6 = getApplicationContext().getResources().getString(R.string.cheesecake6) ;
        onView(withText(cheesecakeStep6)).check(matches(isDisplayed()));
    }

    @Test
    public void clickCheeseCake_enterRecipieAndDetailsActivityForCheeseCake_clickNext() {
        onView(allOf(withText("Cheesecake"))).perform(click());

        onView(ViewMatchers.withId(R.id.recipie_frgament)).perform(RecyclerViewActions.scrollTo(
                hasDescendant(withText(cheesecakeStep_6))));

        onView(ViewMatchers.withText(cheesecakeStep_6))
                .perform(click());

        String cheesecakeStep6 = getApplicationContext().getResources().getString(R.string.cheesecake6) ;
        onView(withText(cheesecakeStep6)).check(matches(isDisplayed()));

        onView(withId(R.id.nextButton)).perform(click());
        String cheesecakeStep7 = getApplicationContext().getResources().getString(R.string.cheesecake7) ;
        onView(withText(cheesecakeStep7)).check(matches(isDisplayed()));

    }



}
