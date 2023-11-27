package com.achsanit.jokesapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.achsanit.jokesapp.databinding.FragmentSearchBinding
import com.achsanit.jokesapp.utils.Resource
import com.achsanit.jokesapp.utils.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModel()
    private val jokesAdapter: JokesAdapter by lazy {
        JokesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            rvJokes.apply {
                adapter = jokesAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            homeViewModel.jokes.observe(viewLifecycleOwner) { result ->
                when(result) {
                    is Resource.Loading -> pbLoading.makeVisible()
                    is Resource.Success -> {
                        pbLoading.makeGone()
                        result.data?.let {
                            if (it.isNotEmpty()) {
                                jokesAdapter.submitData(it)
                                rvJokes.makeVisible()
                                tvNoJokes.makeGone()
                            } else {
                                rvJokes.makeGone()
                                tvNoJokes.makeVisible()
                            }
                        }
                    }
                    else -> {
                        pbLoading.makeVisible()
                        android.widget.Toast.makeText(
                            requireContext(),
                            "Error when search jokes",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}