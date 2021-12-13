package com.example.lunchticket.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.lunchticket.LoginActivity
import com.example.lunchticket.ProfilePictureActivity
import com.example.lunchticket.R
import com.example.lunchticket.databinding.FragmentNotPictureBinding
import com.example.lunchticket.dialog.SelectImageFragment
import com.example.lunchticket.util.Constants
import java.io.File
import java.util.*

class NotPictureFragment : Fragment() {

    // Fragmento para que el usuario pueda tomar una foto si no tiene una

    private var _binding: FragmentNotPictureBinding? = null
    private val binding get() = _binding!!
    private var file: File? = null
    private var uri: Uri? = null
    private var thisContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotPictureBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.userNameTV.text = Constants.name
        binding.userCodeTV.text = Constants.code

        val cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onResult)

        (activity as ProfilePictureActivity).requestPermissions(
            arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 1
        )

        binding.photoBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(thisContext!!, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                file = File("${activity?.getExternalFilesDir(null)}/placeholder.png")

                uri = activity?.let { context ->
                        FileProvider.getUriForFile(
                            context, thisContext!!.packageName, file!!
                        )
                    }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                //(activity as CreateProfileActivity).path = file?.path.toString()
                cameraLauncher.launch(intent)
            } else {
                /*requestPermissions(
                    arrayOf(
                        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), 1
                )*/
            }
        }
//        Toast.makeText(context,Constants.code,Toast.LENGTH_SHORT).show()
        return view
    }

    companion object {
        fun newInstance() = NotPictureFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            thisContext = context
        }
    }

    private fun onResult(result: ActivityResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            (activity as ProfilePictureActivity).bitmap = BitmapFactory.decodeFile(file?.path)

            (activity as ProfilePictureActivity).supportFragmentManager
                .beginTransaction().replace(
                    R.id.ppFragmentContainer, (activity as ProfilePictureActivity).selectedPictureFragment)
                .addToBackStack("selectedPicture").commit()

            //val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width / 4, bitmap.height / 4, true)
            //binding.photoBtn.setImageBitmap(thumbnail)

        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            Toast.makeText(activity, "No se ha tomado una foto", Toast.LENGTH_SHORT).show()
        }
    }
}