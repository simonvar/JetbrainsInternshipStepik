package space.tritin.jetbrainsinternshipstepik.common

import android.util.Log
import io.reactivex.Observable
import space.tritin.jetbrainsinternshipstepik.api.StepikApi
import space.tritin.jetbrainsinternshipstepik.api.StepikApiDAO
import space.tritin.jetbrainsinternshipstepik.api.StepikRestApi
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourses

/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */
class CoursesManager(private val api: StepikApiDAO = StepikRestApi()) {

    fun getCourses(query: String = "", page: Int): Observable<StepikCourses>{
        return Observable.create{
            subscriber ->
            val callResponse = api.search(query, page)
            val response = callResponse.execute()
            Log.d(javaClass.simpleName, "Call: ${callResponse.request().url()}")


            if (response.isSuccessful){
                val dataResponse = response.body()?.`search-results`
                val courses = dataResponse?.map {
                    StepikCourseItem(it.course.toLong(), it.course_title, it.course_cover)
                }

                val stepikCourses = StepikCourses(page + 1, courses ?: listOf())

                subscriber.onNext(stepikCourses)
                subscriber.onComplete()
            } else {
                Log.d(javaClass.simpleName, "Error: ${response.code()}")
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

}