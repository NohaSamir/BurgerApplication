package com.example.burgerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = ListAdapter()

        //Call animation function after view created
        animateListFromBottomToTop()
    }


    /**
     * set recycler animation with move list animation
     */
    fun animateListFromBottomToTop()
    {
        // load the animation
        val animMove = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.move_list_anim
        )

        //set recycler animation
        recycler.startAnimation(animMove)
    }
}
