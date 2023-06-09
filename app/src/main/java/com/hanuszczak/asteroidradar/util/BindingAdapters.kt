package com.hanuszczak.asteroidradar.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.hanuszczak.asteroidradar.R
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imgUrl", "mediaType", "imgDescription", "imgDescriptionNotShowingYet")
fun bindImage(imgView: ImageView, imgUrl: String?, mediaType: String?, imgDescription: String?, imgDescriptionNotShowingYet: String?) {
    //Before set image to the ImageView I use @string/this_is_nasa_s_picture_of_day_showing_nothing_yet value
    imgView.contentDescription = imgDescriptionNotShowingYet
    imgUrl?.let {
        if ("video" == mediaType) {
            Picasso.with(imgView.context).load(R.drawable.youtube_logo).fit().centerCrop()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imgView)
        } else {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Picasso.with(imgView.context).load(imgUri).fit().centerCrop()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imgView)
        }
        //After set image to the ImageView I use @string/nasa_picture_of_day_content_description_format value
        imgView.contentDescription = imgDescription
    }
}
