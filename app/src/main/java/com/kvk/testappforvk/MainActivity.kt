package com.kvk.testappforvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentOne = FragmentOne()
    private val fragmentTwo = FragmentTwo()
    private val fragmentThree = FragmentThree()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        replaceFragment(fragmentOne)
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.id_menu_1 ->replaceFragment(fragmentOne)
                R.id.id_menu_2 ->replaceFragment(fragmentTwo)
                R.id.id_menu_3 ->replaceFragment(fragmentThree)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

}