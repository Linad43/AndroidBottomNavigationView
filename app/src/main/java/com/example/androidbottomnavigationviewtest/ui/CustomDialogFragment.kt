package com.example.androidbottomnavigationviewtest.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidbottomnavigationviewtest.R

class CustomDialogFragment(
    val tv: TextView,
    val title: String,
    val message: String,
) : DialogFragment() {
    private lateinit var inputTextET: EditText
    private var updatedle: Updateble? = null

    companion object {
        fun checkETisNotEmpty(et: EditText): Boolean {
            if (et.text.isEmpty()) {
                et.setHint("Поле не может быть пустым")
                return false
            } else {
                return true
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog, null)
        builder.setView(dialogView)
        inputTextET = dialogView.findViewById(R.id.inputTextET)
        inputTextET.setText(tv.text)
        return builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Принять") { _, _ ->
                if (checkETisNotEmpty(inputTextET)) {
                    val bundle = Bundle()
                    bundle.putString("key", inputTextET.text.toString())
                    when (title) {
                        "Изменить имя" -> {
                            fragmentManager?.setFragmentResult("name", bundle)
                        }

                        "Изменить номер телефона" -> {
                            fragmentManager?.setFragmentResult("numPhone", bundle)
                        }

                        "Изменить возраст" -> {
                            fragmentManager?.setFragmentResult("age", bundle)
                        }
                    }
                }
            }
            .setNegativeButton("Отмена", null)
            .create()
    }
}
