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

        lvItems.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            // go to activity to load pizza details fragment
            listener!!.onPizzaItemSelected(position) // (3) Communicate with Activity using Listener
        }
    }


    //--OnItemSelectedListener listener;
    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {      // context instanceof YourActivity
            this.listener = context // = (YourActivity) context
        } else {
            throw ClassCastException(context!!.toString() + " must implement PizzaMenuFragment.OnItemSelectedListener")
        }
    }


    // Define the events that the fragment will use to communicate
    interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        fun onPizzaItemSelected(position: Int)
    }

}
