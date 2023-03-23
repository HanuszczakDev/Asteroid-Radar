package com.hanuszczak.asteroidradar.util

import androidx.recyclerview.widget.DiffUtil
import com.hanuszczak.asteroidradar.model.domain.Asteroid

class AsteroidDiffItemCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean =
        (oldItem == newItem)
}