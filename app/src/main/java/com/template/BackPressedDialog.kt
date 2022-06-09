package com.template

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.template.databinding.RandomWallpaperLayoutBinding

class BackPressedDialog(private val image: String): DialogFragment() {

    private lateinit var binding: RandomWallpaperLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = RandomWallpaperLayoutBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity()).setTitle("Recommended wallpaper")
        builder.setView(binding.root)
        with(binding) {
            imageView.setImageDrawable(Drawable.createFromStream(resources.assets.open("wallpapers/$image"), null)!!)
            exitButton.setOnClickListener { requireActivity().finish() }
            setButton.setOnClickListener {
                findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(image))
                dismiss()
            }
        }
        return builder.create()
    }
}