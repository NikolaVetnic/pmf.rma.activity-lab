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
public class Test4_DoubleRotateActivityTwoTest {

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
	public void testDoubleRotateActivityTwo() {

		// ==================== Section One =====================
		assertTrue("DoubleRotateActivityTwoTest failed: Section One: ActivityOne did not load correctly",
				solo.waitForActivity(ActivityOne.class, timeout));

		// ==================== Section Two =====================
		solo.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo);
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bLaunchActivityTwo));

		assertTrue("DoubleRotateActivityTwoTest failed: Section Two: ActivityTwo did not load correctly",
				solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));

		// ==================== Section Three =====================
		solo.setActivityOrientation(Solo.LANDSCAPE);

		assertTrue("DoubleRotateActivityTwoTest failed: Section Three: ActivityTwo did not correctly load after first LANDSCAPE rotation.",
				solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));

		// ==================== Section Four =====================
		solo.setActivityOrientation(Solo.PORTRAIT);

		assertTrue("DoubleRotateActivityTwoTest failed: Section Four: ActivityTwo did not correctly load after second PORTRAIT rotation.",
				solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));

		assertTrue("DoubleRotateActivityTwoTest failed: Section Four: onCreate() count was off for ActivityTwo.",
				solo.waitForText("onCreate\\(\\) calls: 3"));
		assertTrue("DoubleRotateActivityTwoTest failed: Section Four: onStart() count was off for ActivityTwo.",
				solo.waitForText("onStart\\(\\) calls: 3"));
		assertTrue("DoubleRotateActivityTwoTest failed: Section Four: onResume() count was off for ActivityTwo.",
				solo.waitForText("onResume\\(\\) calls: 3"));
		assertTrue("DoubleRotateActivityTwoTest failed: Section Four: onRestart() count was off for ActivityTwo.",
				solo.waitForText("onRestart\\(\\) calls: 0"));
	}
}
