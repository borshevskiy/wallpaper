package com.template

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.template.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<SecondFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.imageView.setImageDrawable(Drawable.createFromStream(resources.assets.open("wallpapers/${args.wallpaper}"), null)!!)
        binding.buttonSecond.setOnClickListener {
            WallpaperManager.getInstance(requireContext()).setBitmap(BitmapFactory.decodeStream(resources.assets.open("wallpapers/${args.wallpaper}")))
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}