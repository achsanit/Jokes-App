package com.achsanit.jokesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.achsanit.jokesapp.databinding.FragmentHomeBinding
import com.achsanit.jokesapp.utils.Resource
import com.achsanit.jokesapp.utils.makeGone
import com.achsanit.jokesapp.utils.makeInvisible
import com.achsanit.jokesapp.utils.makeVisible
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnRandom.setOnClickListener {
                homeViewModel.randomJoke()
            }

            homeViewModel.randomJoke.observe(viewLifecycleOwner) { result ->
                when(result) {
                    is Resource.Loading -> {
                        pbLoading.makeVisible()
                        tvInstruction.makeGone()
                    }
                    is Resource.Success -> {
                        pbLoading.makeGone()
                        cvJokes.makeVisible()

                        tvJokes.text = result.data?.value
                    }
                    else -> {
                        pbLoading.makeGone()
                        cvJokes.makeInvisible()
                        tvInstruction.makeVisible()

                        Toast.makeText(
                            requireContext(),
                            "Something went wrong, sorry",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

        homeViewModel.randomJoke.observe(viewLifecycleOwner) { result ->
            result.data?.let {
                binding.tvInstruction.makeGone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}