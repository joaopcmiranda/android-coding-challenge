package com.codepath.mypizza.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.codepath.mypizza.R
import com.codepath.mypizza.data.Pizza
import com.codepath.mypizza.extensions.onLayoutChanged
import com.codepath.mypizza.identifier.Identifier
import kotlinx.android.synthetic.main.fragment_pizza_menu.*

/**
 * Created by Shyam Rokde on 8/5/16.
 */
class PizzaMenuFragment : Fragment() {

    private var itemsAdapter: ArrayAdapter<String>? = null

    private var listener: OnItemSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemsAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, Pizza.pizzaMenu)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_pizza_menu, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lvItems.adapter = itemsAdapter

        lvItems.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // go to activity to load pizza details fragment
            listener!!.onPizzaItemSelected(position) // (3) Communicate with Activity using Listener
        }

        val id = Identifier()
        lvItems.onLayoutChanged { v: View ->
            id.attachView(v)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {      // context instanceof YourActivity
            this.listener = context // = (YourActivity) context
        } else {
            throw ClassCastException("$context must implement PizzaMenuFragment.OnItemSelectedListener")
        }
    }

    // Define the events that the fragment will use to communicate
    interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        fun onPizzaItemSelected(position: Int)
    }

}
