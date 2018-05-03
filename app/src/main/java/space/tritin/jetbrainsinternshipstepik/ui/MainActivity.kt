package space.tritin.jetbrainsinternshipstepik.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import space.tritin.jetbrainsinternshipstepik.R

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar!!.title = "Stepik"

        main_pager.adapter = MainPagerAdapter(supportFragmentManager)
        main_tab_layout.setupWithViewPager(main_pager)
    }
}
