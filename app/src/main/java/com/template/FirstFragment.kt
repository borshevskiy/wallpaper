package com.template

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.template.databinding.FragmentFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstFragment : Fragment(), View.OnTouchListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { PicturesAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setupRecyclerView()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                BackPressedDialog(resources.assets.list("wallpapers")!!.toList().shuffled()[0]).show(parentFragmentManager, "dialog")
            }
        })
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter.wallPapers = resources.assets.list("wallpapers")!!.toList()
//        binding.rvWallpapers.setOnTouchListener { _, event ->
//            if (event.pointerCount == 2) {
//                Toast.makeText(requireActivity(), "After 1 second you will exit the application", Toast.LENGTH_SHORT).show()
//                CoroutineScope(Dispatchers.Main).launch {
//                    delay(1000)
//                    activity?.finish()
//                }
//            }
//            true
//        }
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event!!.pointerCount == 2) {
            Toast.makeText(requireActivity(), "After 1 second you will exit the application", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                activity?.finish()
            }
        }
        return true
    }
}