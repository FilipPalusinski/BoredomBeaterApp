package pl.studioandroida.niemanudy.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityDatabaseTest: TestCase(){

    private lateinit var db: ActivityDatabase
    private lateinit var dao: ActivityDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            ActivityDatabase::class.java
        ).build()
        dao = db.activityDao()
    }

    @After
    fun closeDB(){
        db.close()
    }


    @Test
    fun writeAndReadActivityEntity() = runBlocking{
        val exampleActivity = ActivityEntity(
            "Go for a run",
            "Sport",
            0,
            "https://en.wikipedia.org/wiki/Running",
            1
        )
        dao.insertActivity(exampleActivity)
        val activityList = dao.getActivities()
        assertThat(activityList).contains(exampleActivity)
    }

    @Test
    fun deleteActivityEntity() = runBlocking {
        val exampleActivity = ActivityEntity(
            "Go for a run",
            "Sport",
            0,
            "https://en.wikipedia.org/wiki/Running",
            1
        )
        dao.insertActivity(exampleActivity)
        dao.deleteActivity(exampleActivity)
        val activityList = dao.getActivities()
        assertThat(activityList).doesNotContain(exampleActivity)

    }

}