package com.hanuszczak.asteroidradar.viewmodel.adapter

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanuszczak.asteroidradar.R
import com.hanuszczak.asteroidradar.databinding.AsteroidItemBinding
import com.hanuszczak.asteroidradar.model.domain.Asteroid
import com.hanuszczak.asteroidradar.util.AsteroidDiffItemCallback

class AsteroidAdapter(val clickListener: (asteroid: Asteroid) -> Unit)
    : ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(AsteroidDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsteroidViewHolder =
        AsteroidViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class AsteroidViewHolder(val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): AsteroidViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
                return AsteroidViewHolder(binding)
            }
        }

        fun bind(item: Asteroid, clickListener: (asteroid: Asteroid) -> Unit) {
            binding.asteroid = item
            binding.asteroidImgStatus.contentDescription =
                when (item.isPotentiallyHazardous) {
                    true -> binding.asteroidImgStatus.context.getString(R.string.potentially_hazardous_asteroid_image)
                    false -> binding.asteroidImgStatus.context.getString(R.string.not_hazardous_asteroid_image)
                }
            binding.root.setOnClickListener { clickListener(item) }
        }
    }
}