package com.mad.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.mad.navigationdemo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener(){

            val inputName :String = binding.inputName.text.toString()
            val inputPassword :String = binding.inputPassword.text.toString()

            if (inputName == "abc" && inputPassword == "123"){

                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_questionFragment)

            }
            else{

            Toast.makeText(context, "Invalid user name or password", Toast.LENGTH_LONG).show()
            }

        }


        return binding.root
    }

}