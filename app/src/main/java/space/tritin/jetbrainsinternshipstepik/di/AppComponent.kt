package space.tritin.jetbrainsinternshipstepik.di

import dagger.Component
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.FavoriteFragmentPresenter
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.FavoritePresenter
import space.tritin.jetbrainsinternshipstepik.mvp.presenters.SearchFragmentPresenter
import javax.inject.Singleton

/**
 * <p>Date: 05.05.18</p>
 * @author Simon
 */
@Singleton
@Component
interface AppComponent {

    fun inject(searchFragmentPresenter: SearchFragmentPresenter)
    fun inject(favoriteFragmentPresenter: FavoriteFragmentPresenter)
    fun inject(favoritePresenter: FavoritePresenter)

}