package dev.abdujabbor.db_browser.fragments

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.abdujabbor.db_browser.MyData
import dev.abdujabbor.db_browser.R
import dev.abdujabbor.db_browser.databinding.FragmentAddBinding
import dev.abdujabbor.db_browser.db.My_Db_Browser
import dev.abdujabbor.db_browser.model.Uset
import dev.abdujabbor.db_browser.notification.MyService

class AddFragment : Fragment() {
    private val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (MyData.edtoradd == 0) {
            val myDbHelper = My_Db_Browser(binding.root.context)
            binding.apply {
                btnSave.setOnClickListener {
                    if(binding.numberEdt.text!!.length!=12){
                        binding.numberLayout.error = "Uzbek number length is 7"
                    }else{
                        val myCOnat = Uset(
                            nameEdt.text.toString(), numberEdt.text.toString()
                        )
                        myDbHelper.addContact(myCOnat)
                        Toast.makeText(binding.root.context, "Saved", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
        } else if (MyData.edtoradd == 1) {
            val list = requireArguments().getSerializable("list") as Uset
            binding.nameEdt.setText(list.name)
            binding.numberEdt.setText(list.number)
            binding.btnSave.setOnClickListener {
                list.name = binding.nameEdt.text.toString()
                list.number = binding.numberEdt.text.toString()
                My_Db_Browser(binding.root.context).updateUser(list)
                Toast.makeText(binding.root.context, "Data updated", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()

            }
        }
        return binding.root
    }
}