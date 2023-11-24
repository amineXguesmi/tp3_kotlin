package com.example.tp__4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Switch

class MainActivity : AppCompatActivity() , ActionMode.Callback{
    private lateinit var switchWidget: Switch
    private lateinit var buttonSet : Button
    private lateinit var buttonDigital : Button
    private lateinit var fragmentClock: FragmentClock
    private  lateinit var actionMode: ActionMode
    private var currentTheme = R.style.Theme_Tp__4
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchWidget = findViewById(R.id.switch1)
        buttonDigital = findViewById(R.id.button)
        buttonSet = findViewById(R.id.button2)

        fragmentClock = FragmentClock()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragmentClock, "ClockFragment")
            .addToBackStack(null)
            .commit()

        switchWidget.setOnClickListener { setTime(it) }
        buttonDigital.setOnClickListener { setDigitalClock() }
        buttonSet.setOnClickListener { setNumericClock() }
        buttonSet.setOnLongClickListener{
            actionMode = this@MainActivity.startActionMode(this@MainActivity)!!
            return@setOnLongClickListener true
        }
    }

    fun setTime(view: View?) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragmentClock = FragmentClock()
        val bundle = Bundle()
        bundle.putBoolean("digitalOK", this.switchWidget.isChecked)
        fragmentClock.arguments = bundle
        transaction.replace(R.id.fragment, fragmentClock)
        transaction.commit()
    }
    private fun setDigitalClock() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val currentFragment = fragmentManager.findFragmentByTag("ClockFragment")
        if (currentFragment is FragmentClock) {
            val bundle = Bundle()
            bundle.putBoolean("digitalOK", true)
            currentFragment.arguments = bundle
            transaction.replace(R.id.fragment, currentFragment)
            transaction.commit()
        }
    }

    private fun setNumericClock() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val currentFragment = fragmentManager.findFragmentByTag("ClockFragment")
        if (currentFragment is FragmentClock) {
            val bundle = Bundle()
            bundle.putBoolean("digitalOK", false)
            currentFragment.arguments = bundle
            transaction.replace(R.id.fragment, currentFragment)
            transaction.commit()
        }    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if(item.itemId == R.id.action_switch)
            {
                this.switchWidget.isChecked = !this.switchWidget.isChecked
                setTime(null)
            }
            return super.onOptionsItemSelected(item)
        }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.menu_rf, menu)
            return true
        }
    override fun onCreateActionMode(actionMode: ActionMode, menu: Menu?): Boolean {
        val inflater: MenuInflater = actionMode.menuInflater
        inflater.inflate(R.menu.context_mode_menu, menu)
        return true
    }

    override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menuItem: MenuItem?): Boolean {
        return when (menuItem?.itemId) {
            R.id.action_color -> {
                this.buttonSet.setBackgroundColor(
                    resources.getColor(
                        R.color.teal_200
                    )
                )
                actionMode?.finish()
                true
            }
            R.id.action_theme -> {
                if (currentTheme == R.style.Theme_Tp__4) {
                    currentTheme = R.style.Theme2
                    buttonSet.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_200
                        )
                    )
                    buttonDigital.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_200
                        )
                    )
                    switchWidget.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_200
                        )
                    )
                } else {
                    currentTheme = R.style.Theme_Tp__4
                    setTheme(R.style.Theme_Tp__4)
                    buttonSet.setBackgroundColor(
                        resources.getColor(
                            R.color.teal_200
                        )
                    )
                    buttonDigital.setBackgroundColor(
                        resources.getColor(
                            R.color.teal_200
                        )
                    )
                    switchWidget.setBackgroundColor(
                        resources.getColor(
                            R.color.teal_200
                        )
                    )
                }
                actionMode?.finish()
                true
            }
            else -> false
        }
    }



    override fun onDestroyActionMode(p0: ActionMode?) {
    }

}