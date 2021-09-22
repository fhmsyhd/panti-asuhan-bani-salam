package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.R
import org.fhmsyhdproject.pantiasuhandhuafabanisalam.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainActivityEspressoTest {

    private val dataActivity = DataDummy.activityData()
    private val dataArticle = DataDummy.articleData()
    private val dataNeeds = DataDummy.needsData()

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(4000)
    }

    @Test
    fun loadHome(){
        onView(withId(R.id.rv_activity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_activity)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.rv_article)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_article)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    @Test
    fun loadActivityHome(){
        onView(withId(R.id.rv_activity)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(withText(dataActivity[0].title)))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(withText(dataActivity[0].date)))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(withText(dataActivity[0].content)))
    }

    @Test
    fun loadArticleHome(){
        onView(withId(R.id.rv_article)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(withText(dataArticle[0].title)))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(withText(dataArticle[0].date)))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(withText(dataArticle[0].content)))
    }

    @Test
    fun loadDonation(){
        onView(withId(R.id.page_donation)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_bank)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadScanQrCode(){
        onView(withId(R.id.page_donation)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withText("Scan Kode QR")).perform(ViewActions.click())
        onView(withId(R.id.image_qr)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadNeeds(){
        onView(withId(R.id.page_donation)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_needs)).perform(ViewActions.click())
        onView(withId(R.id.rv_needs)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_needs)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_judul_detail)).check(ViewAssertions.matches(withText(dataNeeds[0].title)))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_tanggal_artikel)).check(ViewAssertions.matches(withText(dataNeeds[0].date)))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_isi_detail)).check(ViewAssertions.matches(withText(dataNeeds[0].content)))
    }

    @Test
    fun loadAbout(){
        onView(withId(R.id.page_about)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_judul_latar)).perform(ViewActions.click())
        onView(withId(R.id.tv_isi_latar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(R.id.tv_judul_visi)).perform(ViewActions.click())
//        onView(withId(R.id.tv_isi_visi)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(R.id.tv_judul_misi)).perform(ViewActions.click())
//        onView(withId(R.id.tv_isi_misi)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDataAnak(){

    }
}