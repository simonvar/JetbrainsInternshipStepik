package space.tritin.jetbrainsinternshipstepik

import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import space.tritin.jetbrainsinternshipstepik.api.StepikApiDAO
import space.tritin.jetbrainsinternshipstepik.api.StepikCoursesListDataResponse
import space.tritin.jetbrainsinternshipstepik.api.StepikSearchResponse
import space.tritin.jetbrainsinternshipstepik.common.CoursesManager
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourses

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
class CoursesManagerTest {

    var testSub = TestObserver<StepikCourses>()
    var apiMock = mock<StepikApiDAO>()
    var callMock = mock<Call<StepikSearchResponse>>()

    @Before
    fun setup() {
        testSub = TestObserver()
        apiMock = mock()
        callMock = mock()
        `when`(apiMock.search(anyString(), anyInt())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        // prepare
        val redditNewsResponse = StepikSearchResponse(StepikCoursesListDataResponse( listOf(), 1))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        // call
        val newsManager = CoursesManager(apiMock)
        newsManager.getCourses("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testError() {
        // prepare
        val responseError = Response.error<StepikSearchResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        `when`(callMock.execute()).thenReturn(responseError)

        // call
        val newsManager = CoursesManager(apiMock)
        newsManager.getCourses("").subscribe(testSub)

        // assert
        assert(testSub.errors().size == 1)
    }

    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)


}