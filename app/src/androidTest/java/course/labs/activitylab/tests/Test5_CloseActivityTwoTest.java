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
public class Test5_CloseActivityTwoTest {

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
	public void testCloseActivityTwo() {

		// ==================== Section One =====================
		assertTrue("CloseActivityTwoTest failed: Section One: ActivityOne did not load correctly",
				solo.waitForActivity(ActivityOne.class, timeout));

		// ==================== Section Two =====================
		solo.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo);
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bLaunchActivityTwo));

		assertTrue("CloseActivityTwoTest failed: Section Two: ActivityTwo did not load correctly",
				solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));

		// ==================== Section Three =====================
		solo.waitForView(course.labs.activitylab.R.id.bClose);
		solo.sleep(sleep);
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bClose));

		assertTrue("CloseActivityTwoTest failed: Section Three: ActivityOne did not load correctly after ActivityTwo closed",
				solo.waitForActivity(ActivityOne.class, timeout));

		assertTrue("CloseActivityTwoTest failed: Section Three: onCreate() count was off for ActivityOne.",
				solo.waitForText("onCreate\\(\\) calls: 1"));
		assertTrue("CloseActivityTwoTest failed: Section Three: onStart() count was off for ActivityOne.",
				solo.waitForText("onStart\\(\\) calls: 2"));
		assertTrue("CloseActivityTwoTest failed: Section Three: onResume() count was off for ActivityOne.",
				solo.waitForText("onResume\\(\\) calls: 2"));
		assertTrue("CloseActivityTwoTest failed: Section Three: onRestart() count was off for ActivityOne.",
				solo.waitForText("onRestart\\(\\) calls: 1"));
	}
}
