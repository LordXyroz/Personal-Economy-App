package com.example.personaleconomy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var mainFragment: MainFragment? = null
    private var settingsFragment: SettingsFragment? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var newExpenseButton: FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_icon)

        mainFragment = MainFragment.newInstance()
        settingsFragment = SettingsFragment.newInstance()

        supportFragmentManager.commit {
            add(R.id.fragment_view, mainFragment!!)
            setReorderingAllowed(true)
            show(mainFragment!!)
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        newExpenseButton = findViewById(R.id.new_expense_button)

        newExpenseButton!!.setOnClickListener { view ->


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            gotoSettings()
        }
        android.R.id.home -> {
            drawerLayout?.openDrawer(GravityCompat.START)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun gotoSettings(): Boolean {
        if (supportFragmentManager.findFragmentByTag("settings") == null) {
            supportFragmentManager.commit{
                replace(R.id.fragment_view, settingsFragment!!, "settings")
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        supportActionBar?.title = "Settings"

        return true
    }
}