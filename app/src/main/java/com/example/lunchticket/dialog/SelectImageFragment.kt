package com.example.lunchticket.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.example.lunchticket.R
import com.example.lunchticket.RegisterNewRestaurantActivity
import com.example.lunchticket.databinding.FragmentSelectImageBinding
import com.example.lunchticket.util.UtilDomi
import java.io.File
import java.util.*

class SelectImageFragment : DialogFragment() {

    // Es un dialogFragment para elegir entre galeria y camara, se usa en el registro de
    // nuevos restaurantes

    private lateinit var binding: FragmentSelectImageBinding
    private var file: File? = null
    private var uri: Uri? = null
    var newContext: Context? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectImageBinding.inflate(inflater, container, false)

        val cameraLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
                ::onCameraResult
            )
        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGalleryResult
        )

        binding.fromCameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${activity?.getExternalFilesDir(null)}/newRestaurantImage.png")
            uri =
                activity?.let { it1 ->
                    FileProvider.getUriForFile(
                        it1,
                        "com.example.lunchticket",
                        file!!
                    )
                }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            (activity as RegisterNewRestaurantActivity).path = file?.path.toString()
            cameraLauncher.launch(intent)
        }

        binding.fromGalleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        return binding.root
    }

    companion object {
        fun newInstance() = SelectImageFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            newContext = context
        }
    }

    private fun onCameraResult(result: ActivityResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(file?.path)
            setThumbnail(bitmap)
            dismiss()
        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            Toast.makeText(activity, "No se tomó la foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGalleryResult(result: ActivityResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val uriImage = result.data?.data
            uriImage?.let {
                val galleryPath = UtilDomi.getPath(newContext!!, it)
                //Log.e(">>>", "" + path2)
                val bitmap =
                    BitmapFactory.decodeStream(newContext!!.contentResolver.openInputStream(it))
                // Log.e(">>>", "" + bitmap)
                (activity as RegisterNewRestaurantActivity).path = galleryPath!!
                setThumbnail(bitmap)
                dismiss()
            }
        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            Toast.makeText(activity, "No se seleccionó una imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setThumbnail(bitmap: Bitmap) {
        val thumbnail =
            Bitmap.createScaledBitmap(bitmap, bitmap.width / 4, bitmap.height / 4, true)
        (activity as RegisterNewRestaurantActivity)
            .binding.previewRestaurantImage.setImageBitmap(
                thumbnail
            )
        (activity as RegisterNewRestaurantActivity)
            .binding.restaurantImageBtn.alpha = 0F

        (activity as RegisterNewRestaurantActivity)
            .binding.previewRestaurantImage.alpha = 1F
    }
}