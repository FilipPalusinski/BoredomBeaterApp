package pl.studioandroida.boredombeater.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import pl.studioandroida.boredombeater.data.mapper.toActivity
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitServiceTest {
    private lateinit var service: ActivityService
    private lateinit var server: MockWebServer
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))//We will use MockWebServers url
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ActivityService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getActivity_sentRequest_receivedExpected() {
        runBlocking {
            // Prepare fake response
            enqueueMockResponse("success_response.json")
            //Send Request to the MockServer
            val responseBody = service.getActivity().toActivity()
            //Request received by the mock server
            val request = server.takeRequest()
            assertNotNull(responseBody)
            assertEquals(responseBody.activity, "Play a game of tennis with a friend")
            assertEquals(request.path, "/activity")
        }
    }


}