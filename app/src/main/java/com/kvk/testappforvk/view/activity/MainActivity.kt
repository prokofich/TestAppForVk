package com.kvk.testappforvk.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kvk.testappforvk.R
import com.kvk.testappforvk.databinding.ActivityMainBinding
import com.kvk.testappforvk.view.MAIN

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this, R.id.fragment_container)

        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.id_menu_1 -> navController?.navigate(R.id.fragmentOne)
                R.id.id_menu_2 -> navController?.navigate(R.id.fragmentTwo)
                R.id.id_menu_3 -> navController?.navigate(R.id.fragmentThree)
            }
            true
        }

    }

}