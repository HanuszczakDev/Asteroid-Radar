package com.hanuszczak.asteroidradar.view.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hanuszczak.asteroidradar.R
import com.hanuszczak.asteroidradar.databinding.FragmentMainBinding
import com.hanuszczak.asteroidradar.viewmodel.adapter.AsteroidAdapter
import com.hanuszczak.asteroidradar.viewmodel.main.MainViewModel
import com.hanuszczak.asteroidradar.viewmodel.main.MainViewModelFactory
import com.hanuszczak.asteroidradar.viewmodel.main.OptionMenu

class MainFragment : Fragment(), MenuProvider {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, MainViewModelFactory(activity.application))[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        val adapter = AsteroidAdapter {
            viewModel.onAsteroidClicked(it)
        }

        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroids.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.navigateToAsteroid.observe(viewLifecycleOwner) { asteroid ->
            asteroid?.let {
                val action = MainFragmentDirections.actionShowDetail(asteroid)
                this.findNavController().navigate(action)
                viewModel.onAsteroidNavigated()
            }
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_overflow_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        viewModel.optionMenu.value = (
            when (menuItem.itemId) {
                R.id.show_all_menu -> {
                    OptionMenu.SHOW_ALL
                }
                R.id.show_today_menu -> {
                    OptionMenu.SHOW_TODAY
                }
                else -> {
                    OptionMenu.SHOW_WEEK
                }
            }
        )
        return true
    }
}
