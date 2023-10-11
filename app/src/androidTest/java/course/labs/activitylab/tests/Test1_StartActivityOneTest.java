package course.labs.activitylab.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.robotium.solo.Solo;

import course.labs.activitylab.ActivityOne;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Test1_StartActivityOneTest {

	@Rule
	public ActivityTestRule<ActivityOne> mActivityRule = new ActivityTestRule<>(ActivityOne.class);

	private Solo solo;
	private int timeout = 20000;

	@Before
	public void setUp() throws Exception {
		solo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityRule.getActivity());
	}


	@After
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	@Test
	public void testRun() {

		// ==================== Section One =====================
		assertTrue("StartActivityOneTest failed: Section One: ActivityOne did not correctly load",
				solo.waitForActivity(course.labs.activitylab.ActivityOne.class, timeout));

		// ==================== Section Two =====================
		assertTrue("StartActivityOneTest failed: Section Two: onCreate() count was off for ActivityOne",
				solo.waitForText("onCreate\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed: Section Two: onStart() count was off for ActivityOne",
				solo.waitForText("onStart\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed: Section Two: onResume() count was off for ActivityOne",
				solo.waitForText("onResume\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed: Section Two: onRestart() count was off for ActivityOne",
				solo.waitForText("onRestart\\(\\) calls: 0"));
	}
}
