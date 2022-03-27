package ru.webim.tz.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.core.view.isVisible
import ru.webim.tz.R
import ru.webim.tz.databinding.FragmentAuthBinding
import ru.webim.tz.di.auth.AuthFragmentScope
import ru.webim.tz.domain.model.AuthError
import ru.webim.tz.domain.model.AuthError.UNKNOWN
import ru.webim.tz.domain.model.AuthError.WRONG_LOGIN
import ru.webim.tz.domain.model.AuthError.WRONG_PASSWORD
import ru.webim.tz.presentation.MainActivity
import ru.webim.tz.presentation.base.BaseApplication
import ru.webim.tz.presentation.base.BaseFragment
import ru.webim.tz.presentation.util.toIntSafe
import javax.inject.Inject

@AuthFragmentScope
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("AuthFragment", "onCreate")
        (activity?.application as? BaseApplication)
            ?.appComponent
            ?.authComponent()
            ?.create()
            ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.authState.observe(viewLifecycleOwner) {
            if (it.isAuthenticated) {
                goToListFragment()
            }
            binding?.progressBar?.isVisible = it.isLoading
            setErrorState(it.error)
        }
    }


    private fun setErrorState(error: AuthError?) {
        if (error != null) {
            binding?.progressBar?.isVisible = false
        }
        val loginText = if (error == WRONG_LOGIN) getString(R.string.wrong_login) else ""
        val passwordText = if (error == WRONG_PASSWORD) getString(R.string.wrong_password) else ""
        val unknownText = if (error == UNKNOWN) getString(R.string.something_wrong) else ""

        binding?.loginError?.text = loginText
        binding?.passwordError?.text = passwordText
        binding?.unknownErrorTextView?.text = unknownText
    }

    private fun setupBindings() = binding?.apply {
        loginButton.setOnClickListener {
            viewModel.login(
                login = loginEditText.text.toString(),
                password = passwordEditText.text.toString().toIntSafe()
            )
        }

        passwordEditText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if ((event?.action == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)
                ) {
                    loginButton.callOnClick()
                    return true
                }
                return false
            }
        })
    }

    private fun goToListFragment() {
        (activity as? MainActivity)?.openListFragment()
    }
}