package com.example.burgerapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * ToDo introduction
 * 1- Zoom text from 0 to actual size
 * 2- Animate text in a wavy way zigzag (Unimplemented)
 * 3- translate burger from bottom of the cell
 * 4- translate new icon and Zoom it
 */

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

        private val background = itemView.rootLayout

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

            background.setBackgroundResource(
                when (temp) {
                    0 -> R.color.backgroundGreen
                    1 -> R.color.backgroundRedDark
                    2 -> R.color.backgroundBrown
                    3 -> R.color.backgroundTan
                    else -> 0
                }
            )
        }

        /**
         * ToDo 2 : Zoom Text
         */
        private fun zoomText() {
            itemView.titleTextView.startAnimation(textZoomAnim)
            itemView.subtitleTextView.startAnimation(textZoomAnim)
            itemView.priceTextView1.startAnimation(textZoomAnim)
            itemView.priceTextView2.startAnimation(textZoomAnim)
        }

        /**
         * ToDo 3 : Move burger Image from bottom to top
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
            newMoveAnim.setAnimationListener(object : Animation.AnimationListener{
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