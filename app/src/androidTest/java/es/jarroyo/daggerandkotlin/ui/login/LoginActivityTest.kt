package es.jarroyo.daggerandkotlin.ui.login

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.ui.home.activity.HomeActivity
import es.jarroyo.daggerandkotlin.ui.login.activity.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    companion object {
        val EMAIL_VALID: String = "email@email.com"
        val EMAIL_NOT_VALID: String = "emailNotValid"
        val PASSWORD_VALID: String = "Password1"
        val PASSWORD_NOT_VALID: String = "passnotvalid"
    }

    @Rule
    @JvmField
    val activity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)


    /**
     * Test that checks if you put an incorrect mail show an alert
     */
    @Test
    fun testEmailNotCorrect() {
        // Type incorrect mail
        onView(withId(R.id.input_email)).perform(typeText(EMAIL_NOT_VALID))

        // Click Login
        onView(withId(R.id.button_login)).perform(click())

        // No Navigate to home / InputEmail is Showed
        onView(withId(R.id.input_email)).check(matches(isDisplayed()))
    }

    /**
     * Test that checks if you put an incorrect mail show an alert
     */
    @Test
    fun testEmailCorrectAndPasswordNotCorrect() {
        // Type correct mail
        onView(withId(R.id.input_email)).perform(typeText(EMAIL_VALID))
        
        // Type NOTcorrect password
        onView(withId(R.id.input_password)).perform(typeText(PASSWORD_NOT_VALID))

        // Click Login
        onView(withId(R.id.button_login)).perform(click())

        // No Navigate to home / InputEmail is Showed
        onView(withId(R.id.input_email)).check(matches(isDisplayed()))
    }

    /**
     * Test that checks if you write a valid email and password - Navigate to Home
     */
    @Test
    fun testOnClickLoginNavigateToHome() {
        Intents.init()
        
        // Type correct mail
        onView(withId(R.id.input_email)).perform(typeText(EMAIL_VALID))

        // Type correct password
        onView(withId(R.id.input_password)).perform(typeText(PASSWORD_VALID))

        // Click Login - Navigate to Home
        onView(withId(R.id.button_login)).perform(click())
        intended(allOf(hasComponent(hasClassName(HomeActivity::class.java.name))))

        Intents.release()
    }

}