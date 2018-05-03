package space.tritin.jetbrainsinternshipstepik.api

import retrofit2.Call

/**
 * <p>Date: 03.05.18</p>
 * @author Simon
 */
interface StepikApiDAO {

    fun search(query: String, page: Int): Call<StepikCoursesResponse>

}