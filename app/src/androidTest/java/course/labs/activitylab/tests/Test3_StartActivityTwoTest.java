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
public class Test3_StartActivityTwoTest {

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
	public void testStartActivityTwo() {

		// ==================== Section One =====================
		assertTrue("StartActivityTwoTest failed: Section One: ActivityOne did not load correctly",
				solo.waitForActivity(ActivityOne.class, timeout));

		// ==================== Section Two =====================
		solo.waitForView(course.labs.activitylab.R.id.bLaunchActivityTwo);
		solo.clickOnView(solo.getView(course.labs.activitylab.R.id.bLaunchActivityTwo));

		assertTrue("StartActivityTwoTest failed: Section Two: ActivityTwo did not load correctly",
				solo.waitForActivity(course.labs.activitylab.ActivityTwo.class, timeout));

		assertTrue("StartActivityTwoTest failed: Section Two: onCreate() count was off for ActivityTwo.",
				solo.waitForText("onCreate\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed: Section Two: onStart() count was off for ActivityTwo.",
				solo.waitForText("onStart\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed: Section Two: onResume() count was off for ActivityTwo.",
				solo.waitForText("onResume\\(\\) calls: 1"));
		assertTrue("StartActivityTwoTest failed: Section Two: onRestart() count was off for ActivityTwo.",
				solo.waitForText("onRestart\\(\\) calls: 0"));
	}
}
