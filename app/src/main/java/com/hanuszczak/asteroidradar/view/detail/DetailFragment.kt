package com.hanuszczak.asteroidradar.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.hanuszczak.asteroidradar.R
import com.hanuszczak.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid

        binding.asteroid = asteroid

        binding.helpButton.contentDescription = resources.getString(R.string.astronomica_unit_explanation)
        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }

        binding.activityMainImageOfTheDay.contentDescription =
            when (asteroid.isPotentiallyHazardous) {
                true -> resources.getString(R.string.potentially_hazardous_asteroid_image)
                false -> resources.getString(R.string.not_hazardous_asteroid_image)
            }

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
