package com.hanuszczak.asteroidradar.view.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hanuszczak.asteroidradar.R
import com.hanuszczak.asteroidradar.databinding.FragmentMainBinding
import com.hanuszczak.asteroidradar.model.Db
import com.hanuszczak.asteroidradar.viewmodel.main.MainViewModel
import com.hanuszczak.asteroidradar.viewmodel.main.MainViewModelFactory

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val asteroidDao = Db.getInstance(application).asteroidDao
        val pictureOfDayDao = Db.getInstance(application).pictureOfDayDao

        val viewModelFactory = MainViewModelFactory(asteroidDao, pictureOfDayDao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
