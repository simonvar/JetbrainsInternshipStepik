package space.tritin.jetbrainsinternshipstepik.api

/**
 * <p>Date: 03.05.18</p>
 * @author Simon
 */
class StepikSearchResponse(val `search-results`: List<StepikCourseResponse>)

class StepikCourseResponse(
        val course: Int,
        val course_title: String,
        val course_cover: String?
)