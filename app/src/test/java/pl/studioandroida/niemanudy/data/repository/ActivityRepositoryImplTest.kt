package pl.studioandroida.niemanudy.data.repository


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.data.remote.toActivity
import pl.studioandroida.niemanudy.data.repository.ActivityRepositoryImpl
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.use_case.GetActivityUseCase
import pl.studioandroida.niemanudy.util.Resource
import retrofit2.Response
import javax.inject.Inject


class ActivityRepositoryImplTest  {

    private val activityService = mock<ActivityService>()
    private val activityRepository = ActivityRepositoryImpl(activityService)

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