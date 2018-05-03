package space.tritin.jetbrainsinternshipstepik.ui.base

import com.arellomobile.mvp.MvpAppCompatFragment
import io.reactivex.disposables.CompositeDisposable


/**
 * <p>Date: 04.05.18</p>
 * @author Simon
 */
open class RxMvpFragment : MvpAppCompatFragment() {

    protected var subscriptions = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isDisposed){
            subscriptions.dispose()
        }
        subscriptions.clear()
    }

}