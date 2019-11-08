package com.software.leonwebmedia.tmobileapp.classes

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.software.leonwebmedia.tmobileapp.model.StyleFetcher


class AutoCompleateAdater(context: Context, resource: Int) : ArrayAdapter<Paint.Style>(
    context,
    resource
), Filterable {

    var mData = ArrayList<Paint.Style>()

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Paint.Style? {
        return mData.get(position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    // A class that queries a web API, parses the data and returns an ArrayList<Style>
                    val fetcher = StyleFetcher()
                    try {
                        mData = fetcher.retrieveResults(constraint.toString())
                    } catch (e: Exception) {
                        Log.e("myException", e.message)
                    }

                    // Now assign the values and count to the FilterResults object
                    filterResults.values = mData
                    filterResults.count = mData.size
                }
                return filterResults
            }

            override fun publishResults(contraint: CharSequence, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

}