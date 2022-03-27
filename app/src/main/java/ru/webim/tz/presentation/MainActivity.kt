package ru.webim.tz.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.webim.tz.presentation.util.LiveData
import ru.webim.tz.R
import ru.webim.tz.databinding.ActivityMainBinding
import ru.webim.tz.presentation.authorization.AuthorizationFragment
import ru.webim.tz.presentation.tickets.ListFragment

private const val AUTH_TAG = "Auth"
private const val TICKET_LIST_TAG = "List"

class MainActivity : AppCompatActivity() {

    private lateinit var connectionLiveData: LiveData

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectionLiveData = LiveData(context = this)
        openAuthFragment()
        connectionLiveData.observe(this) {
            binding.noInternetView.isVisible = it.not()
        }
    }

    // Sample app with only 2 screens, that's why it is written like this
    fun openAuthFragment() {
        if (isAuthFragmentVisible()) return
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, AuthorizationFragment(), AUTH_TAG)
        transaction.commit()
    }

    fun openListFragment() {
        if (isListFragmentVisible()) return
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, ListFragment(), TICKET_LIST_TAG)
        transaction.commit()
    }

    private fun isAuthFragmentVisible(): Boolean {
        val fragment = supportFragmentManager
            .findFragmentByTag(AUTH_TAG)
        return fragment is AuthorizationFragment
    }

    private fun isListFragmentVisible(): Boolean {
        val fragment = supportFragmentManager
            .findFragmentByTag(TICKET_LIST_TAG)
        return fragment is ListFragment
    }
}