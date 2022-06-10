package com.template

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            imageName.text = if (args.wallpaper.contains(NO_NAME)) getString(R.string.app_name) else args.wallpaper.substring(0,args.wallpaper.indexOf("."))
            imageView.setImageDrawable(Drawable.createFromStream(resources.assets.open(WALLPAPERS + "/${args.wallpaper}"), null)!!)
            buttonSecond.setOnClickListener {
                Toast.makeText(requireActivity(), SET_WALLPAPERS_TEXT, Toast.LENGTH_SHORT).show()
                WallpaperManager.getInstance(requireContext()).setBitmap(BitmapFactory.decodeStream(resources.assets.open(WALLPAPERS + "/${args.wallpaper}")))
            }
            layout.closeThreeFingersTouch(requireActivity(), true)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}