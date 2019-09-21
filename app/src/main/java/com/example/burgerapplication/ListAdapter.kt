package com.example.burgerapplication

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * List Adapter bind the list with the following animations
 * 1- Zoom text from 0 to actual size
 * 2- Animate text in a wavy way zigzag (Unimplemented)
 * 3- translate burger from bottom of the cell
 * 4- translate new icon and Zoom it
 */

//ToDo : We need to make the cells rounded while scrolling

// ToDo 1 : We will create 4 rectangles with the 4 colors
// Create rectangle vector using illustrator and export it as svg file
// and then we will open VectAlign (https://github.com/bonnyfone/vectalign) to get a morphing path
// copy morphing path in drawable/rectangle_   .xml

// ToDo 2 : We will create a rounded rectangle with the same previous way
// and then create rectangle path converter in animator/rectangle_path_converter.xml

// ToDo 3 : Create Object animator
// that will connect the vector with it's animation in drawable/rectangle_xxx_animator.xml

// ToDo 4 : Now we need to make the background as an Image view
// Add backgroundImageView in list-item.xml

// ToDo 5: Edit setCellBackground() fun


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    val NUM_OF_ITEMS = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = NUM_OF_ITEMS

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.bindItem(position)
    }

    class ListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val context = itemView.context

        private val backgroundImageView = itemView.backgroundImageView

        //Load zoom animation
        private val textZoomAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_in)

        private var burgerMoveAnim =
            AnimationUtils.loadAnimation(context, R.anim.move_from_bottom_to_top)

        private var newMoveAnim =
            AnimationUtils.loadAnimation(context, R.anim.move_from_bottom_to_top)

        private var newZoomAnim =
            AnimationUtils.loadAnimation(context, R.anim.zoom_out)


        fun bindItem(position: Int) {
            setCellBackground(position)
            zoomText()
            moveBurgerAndNewImages()
        }

        /**
         * Set cell background, we have only 4 colors displayed in order
         * Green - Red - Brown - Tan
         */
        private fun setCellBackground(position: Int) {
            val temp = position % 4

            backgroundImageView.setImageDrawable(
                when (temp) {
                    0 -> ContextCompat.getDrawable(context , R.drawable.rectangle_green_animator)
                    1 -> ContextCompat.getDrawable(context , R.drawable.rectangle_red_animator)
                    2 -> ContextCompat.getDrawable(context , R.drawable.rectangle_brown_animator)
                    3 -> ContextCompat.getDrawable(context , R.drawable.rectangle_tan_animator)
                    else -> ContextCompat.getDrawable(context , R.drawable.rectangle_green_animator)
                }
            )

            val animationDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                backgroundImageView.drawable as AnimatedVectorDrawable

            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }
            animationDrawable.start()
        }

        /**
         * Zoom Text views
         */
        private fun zoomText() {
            itemView.titleTextView.startAnimation(textZoomAnim)
            itemView.subtitleTextView.startAnimation(textZoomAnim)
            itemView.priceTextView1.startAnimation(textZoomAnim)
            itemView.priceTextView2.startAnimation(textZoomAnim)
        }

        /**
         * Move burger Image from bottom to top
         * and after move burger image, we will start to move new image after delay 200ms
         * and on new image animation end, we start to zoom out then zoom in (fill after = false)
         *
         */
        private fun moveBurgerAndNewImages() {
            //Burger Animation
            itemView.burgerImageView.startAnimation(burgerMoveAnim)

            //New Icon Animation
            newMoveAnim.startOffset = 200
            itemView.newImageView.startAnimation(newMoveAnim)
            newMoveAnim.setAnimationListener(object : Animation.AnimationListener {
                //On animation end start zooming
                override fun onAnimationEnd(p0: Animation?) {
                    //Zoom New icon
                    itemView.newImageView.startAnimation(newZoomAnim)
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationStart(p0: Animation?) {

                }
            })

        }

    }


}