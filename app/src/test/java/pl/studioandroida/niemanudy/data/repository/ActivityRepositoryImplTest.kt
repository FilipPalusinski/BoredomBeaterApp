package pl.studioandroida.niemanudy.data.repository


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.studioandroida.niemanudy.data.local.ActivityDao
import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.domain.model.Activity


class ActivityRepositoryImplTest  {

    private val activityService = mock<ActivityService>()
    private val activityDao = mock<ActivityDao>()
    private val activityRepository = ActivityRepositoryImpl(activityService, activityDao)

    @ExperimentalCoroutinesApi
    @Test
    fun getActivityTest() = runTest {
        val remoteActivity = ActivityDto("activity", "type", 3, 4.0, "222", "https://google.com", 0.0)
        val expectedPosts = Activity("activity", "type", 3, "https://google.com")
        whenever(activityService.getActivity()).thenReturn(remoteActivity)
        val result = activityRepository.getActivity().data
        Assert.assertEquals(expectedPosts, result)
    }


}