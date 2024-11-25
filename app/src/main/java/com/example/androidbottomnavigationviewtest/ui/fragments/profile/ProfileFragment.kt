package com.example.androidbottomnavigationviewtest.ui.fragments.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.androidbottomnavigationviewtest.databinding.FragmentProfileBinding
import com.example.androidbottomnavigationviewtest.ui.CustomDialogFragment
import com.example.androidbottomnavigationviewtest.ui.Updateble

class ProfileFragment : Fragment(), Updateble, FragmentResultListener {
    private val GALLERY_REQUEST = 1
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    var photoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTV.setOnClickListener {
            val fragment = CustomDialogFragment(
                binding.nameTV,
                "Изменить имя",
                "Введите имя ниже"
            )
            fragmentManager?.setFragmentResultListener(
                "name",
                fragment,
                this
            )
            fragment.show(requireFragmentManager(), "custom")
        }
        binding.numPhoneTV.setOnClickListener {
            val fragment = CustomDialogFragment(
                binding.numPhoneTV,
                "Изменить номер телефона",
                "Введите номер телефона ниже"
            )
            fragmentManager?.setFragmentResultListener(
                "numPhone",
                fragment,
                this
            )
            fragment.show(requireFragmentManager(), "custom")
        }
        binding.ageTV.setOnClickListener {
            val fragment = CustomDialogFragment(
                binding.ageTV,
                "Изменить возраст",
                "Введите возраст ниже"
            )
            fragmentManager?.setFragmentResultListener(
                "age",
                fragment,
                this
            )
            fragment.show(requireFragmentManager(), "custom")
        }
        binding.imageIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    photoUri = data?.data
                }
            }
        }
        binding.imageIV.setImageURI(photoUri)
    }

    override fun update(text: String, tv: TextView) {
        tv.text = text
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        val value = result.getString("key")
        when(requestKey){
            "name"->{
                binding.nameTV.text = value
            }
            "age"->{
                binding.ageTV.text = value
            }
            "numPhone"->{
                binding.numPhoneTV.text = value
            }
        }
    }
}