package com.example.cabstwo.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.cabstwo.api.PlaceApi;
import com.example.cabstwo.fragment.OneWayFragment;

import java.util.ArrayList;
public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable
{

        ArrayList<String> results;
        int resources;
        Context context;
        PlaceApi placeApi = new PlaceApi();

        public PlaceAutoSuggestAdapter(Context context, int resId) {
            super(context, resId);
            this.context = context;
            this.resources = resId;
        }

    @Override
        public int getCount() {
            return results.size();
        }


        @Override
        public Object getItem(int position) {
            return results.get(position);
        }


        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        results = placeApi.autoComplete(constraint.toString());
                        filterResults.values = results;
                        filterResults.count = results.size();


                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                    if (results != null && results.count > 0)
                    {
                        notifyDataSetChanged();
                    }
                    else
                        {
                        notifyDataSetInvalidated();
                        }
                }

            };
            return filter;
        }

}
