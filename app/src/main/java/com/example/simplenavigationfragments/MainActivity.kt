package com.example.simplenavigationfragments


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simplenavigationfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    // Interface with methods for various interaction from the fragments to the activity
    interface FragmentInteractionListener {
        fun updateToolBarTextView(text: String)
        // Add more methods for other interactions as needed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Link the file .xml with the file .kt
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This method sets the toolbar as the app bar for the activity
        setSupportActionBar(binding.appToolbar)

        // Disable the App Top Bar Title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Hide the default ActionBar in the MainActivity
        supportActionBar?.hide()

        // Hide the BottomNavigationView in the MainActivity
        binding.bottomNavBar.isGone = true

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        // Add the Top-Level destinations (not back button (<-) in the toolbar)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.inicioFragment, R.id.principalFragment, R.id.cuentaFragment,
            R.id.configuracionFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    // Support Navigate Up <- to others Activities or Fragments
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    // updateToolBarTextView method implementation present in the interface
    fun updateToolBarTextView(text: String) {
        binding.toolbarTitle.text = text
    }
}