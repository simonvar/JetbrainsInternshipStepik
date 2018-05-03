package space.tritin.jetbrainsinternshipstepik.api

/**
 * <p>Date: 03.05.18</p>
 * @author Simon
 */
class StepikCoursesResponse(val data: StepikDataResponse)

class StepikDataResponse(
        val children: List<StepikChildrenResponse>,
        val page: Int
)

class StepikChildrenResponse(val data: StepikCoursesDataResponse)

class StepikCoursesDataResponse(
        val id: Int,
        val title: String,
        val description: String,
        val thumbnail: String
)