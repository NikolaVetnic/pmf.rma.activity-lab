package course.labs.activitylab.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import course.labs.activitylab.ActivityOne;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Test2_DoubleRotateActivityOneTest {

    @Rule
    public ActivityTestRule<ActivityOne> mActivityRule = new ActivityTestRule<>(ActivityOne.class);

    private Solo solo;
    private int timeout = 20000;
    private int sleep = 1000;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testDoubleRotation() {

        // ==================== Section One =====================
        assertTrue("DoubleRotateActivityOneTest failed: Section One: ActivityOne did not correctly load",
                solo.waitForActivity(ActivityOne.class, timeout));

        solo.sleep(sleep);

        // ==================== Section Two =====================
        solo.setActivityOrientation(Solo.LANDSCAPE);
        assertTrue("DoubleRotateActivityOneTest failed: Section Two: ActivityOne did not correctly load after first LANDSCAPE rotation.",
                solo.waitForActivity(ActivityOne.class, timeout));

        solo.sleep(sleep);

        // ==================== Section Three =====================
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue("DoubleRotateActivityOneTest failed: Section Three: ActivityOne did not correctly load after second PORTRAIT rotation.",
                solo.waitForActivity(ActivityOne.class, timeout));

        solo.sleep(sleep);

        assertTrue("DoubleRotateActivityOneTest failed: Section Three: onCreate() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onCreate\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed: Section Three: onStart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onStart\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed: Section Three: onResume() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onResume\\(\\) calls: 3"));
        assertTrue("DoubleRotateActivityOneTest failed: Section Three: onRestart() count was off for ActivityOne after second PORTRAIT rotation.",
                solo.searchText("onRestart\\(\\) calls: 0"));
    }
}
