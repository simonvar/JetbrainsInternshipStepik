package space.tritin.jetbrainsinternshipstepik

import android.app.Application
import com.reactiveandroid.ReActiveAndroid
import com.reactiveandroid.ReActiveConfig
import com.reactiveandroid.internal.database.DatabaseConfig
import com.reactiveandroid.internal.database.migration.MigrationContainer
import space.tritin.jetbrainsinternshipstepik.di.AppComponent
import space.tritin.jetbrainsinternshipstepik.di.DaggerAppComponent
import space.tritin.jetbrainsinternshipstepik.mvp.models.AppDatabase
import space.tritin.jetbrainsinternshipstepik.mvp.models.StepikCourseItem

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
class StepikApplication : Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerAppComponent.builder().build()

        val appDatabaseConfig = DatabaseConfig.Builder(AppDatabase::class.java)
                .addModelClasses(StepikCourseItem::class.java)
                .build()

        ReActiveAndroid.init(ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabaseConfig)
                .build())
    }

}