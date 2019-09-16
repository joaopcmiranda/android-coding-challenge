package com.codepath.mypizza.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codepath.mypizza.R
import com.codepath.mypizza.data.Pizza
import com.codepath.mypizza.extensions.onLayoutChanged
import com.codepath.mypizza.identifier.Identifier
import kotlinx.android.synthetic.main.fragment_pizza_detail.*

/**
 * Created by Shyam Rokde on 8/5/16.
 */
class PizzaDetailFragment : Fragment() {
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // Get back arguments
            if (arguments != null) {
                position = arguments!!.getInt("position", 0)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_pizza_detail, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // update view
        tvTitle.text = Pizza.pizzaMenu[position]
        tvDetails.text = Pizza.pizzaDetails[position]

    }

    override fun onStart() {
        super.onStart()
        // attach to identifier
        val id = Identifier()
        tvTitle.onLayoutChanged { view: View -> id.attachView(view) }
        tvDetails.onLayoutChanged { view: View -> id.attachView(view) }
    }

    // Activity is calling this to update view on Fragment
    fun updateView(position: Int) {
        tvTitle.text = Pizza.pizzaMenu[position]
        tvDetails.text = Pizza.pizzaDetails[position]
    }
}
