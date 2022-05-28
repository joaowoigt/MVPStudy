package com.example.mvpstudy.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvpstudy.R
import com.example.mvpstudy.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var binding: FragmentLoginBinding

    private val presenter: LoginContract.Presenter by inject { parametersOf(this@LoginFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        insertListeners()
        return binding.root
    }

    override fun showSuccessToast() {
        Toast.makeText(context, getString(R.string.succes_login_toast), Toast.LENGTH_LONG).show()
    }

    override fun showWrongToast() {
        Toast.makeText(context, getString(R.string.wrong_credentials_toast), Toast.LENGTH_LONG)
            .show()
    }

    override fun showEmptyToast() {
        Toast.makeText(context, getString(R.string.empty_credential_toast), Toast.LENGTH_LONG)
            .show()
    }

    override fun showNullToast() {
        Toast.makeText(context, getString(R.string.null_credentials_toast), Toast.LENGTH_LONG)
            .show()
    }

    override fun insertListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                presenter.handleLogin(
                    loginEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
    }

    override fun navigateToHome() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}