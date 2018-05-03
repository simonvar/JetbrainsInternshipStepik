package space.tritin.jetbrainsinternshipstepik.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * <p>Date: 03.05.18</p>
 * @author Simon
 */
class StepikRestApi: StepikApiDAO {
    private val stepikApi: StepikApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://stepik.org/api")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        stepikApi = retrofit.create(StepikApi::class.java)
    }

    override fun search(query: String, page: Int): Call<StepikCoursesResponse> {
        return stepikApi.search(query, page)
    }
}