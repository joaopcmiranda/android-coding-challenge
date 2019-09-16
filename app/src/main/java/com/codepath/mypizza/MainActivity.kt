package com.codepath.mypizza

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codepath.mypizza.fragments.PizzaDetailFragment
import com.codepath.mypizza.fragments.PizzaMenuFragment

class MainActivity : AppCompatActivity(), PizzaMenuFragment.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("DEBUG", resources.configuration.orientation.toString() + "")

        if (savedInstanceState == null) {
            // Instance of first fragment
            val firstFragment = PizzaMenuFragment()

            // Add Fragment to FrameLayout (flContainer), using FragmentManager
            val ft = supportFragmentManager.beginTransaction()// begin  FragmentTransaction
            ft.add(R.id.flContainer, firstFragment)                                // add    Fragment
            ft.commit()                                                            // commit FragmentTransaction
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val secondFragment = PizzaDetailFragment()
            val args = Bundle()
            args.putInt("position", 0)
            secondFragment.arguments = args          // (1) Communicate with Fragment using Bundle
            val ft2 = supportFragmentManager.beginTransaction()// begin  FragmentTransaction
            ft2.add(R.id.flContainer2, secondFragment)                               // add    Fragment
            ft2.commit()                                                            // commit FragmentTransaction
        }
    }

    override fun onPizzaItemSelected(position: Int) {
        Toast.makeText(this, "Called By Fragment A: position - $position", Toast.LENGTH_SHORT).show()

        // Load Pizza Detail Fragment
        val secondFragment = PizzaDetailFragment()

        val args = Bundle()
        args.putInt("position", position)
        secondFragment.arguments = args          // (1) Communicate with Fragment using Bundle


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment) // replace flContainer
                    //.addToBackStack(null)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, secondFragment) // replace flContainer
                    .addToBackStack(null)
                    .commit()
        }
    }
}
