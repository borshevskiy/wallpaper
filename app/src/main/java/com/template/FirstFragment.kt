package com.template

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.template.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { PicturesAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setupRecyclerView()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                BackPressedDialog(resources.assets.list("wallpapers")!!.toList().shuffled()[0]).show(parentFragmentManager, "dialog")
            }
        })
        binding.layout.setOnTouchListener { _, event ->
            Log.d("TEST", "${event?.pointerCount}")
            true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter.wallPapers = resources.assets.list("wallpapers")!!.toList()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvWallpapers.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }
}