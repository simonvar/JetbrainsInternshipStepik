package space.tritin.jetbrainsinternshipstepik.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <p>Date: 03.05.18</p>
 * @author Simon
 *
 * Api for Stepik
 */
interface StepikApi{

    @GET("api/search-results")
    fun search(@Query("query") query: String, @Query("page") page: Int) : Call<StepikSearchResponse>
}