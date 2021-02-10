package com.example.cabstwo.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.cabstwo.R;
import com.example.cabstwo.activity.CabList;
import com.example.cabstwo.adapter.PlaceAutoSuggestAdapter;
import com.example.cabstwo.adapter.SimpleListAdapter;
import com.example.cabstwo.util.RxSearchObservable;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class OneWayFragment extends Fragment
{
    public static final int AUTOCOMPLETE_REQUEST_CODE = 1231;
    public static final int AUTOCOMPLETE_DESTINATION_CODE = 1232;
    EditText edittext, eReminderTime;
    Button cablist;
    final Calendar myCalendar = Calendar.getInstance();
    TextView searchView;
    TextView rvDestination;
    RecyclerView recyclerView,recyclerView11;
    SimpleListAdapter simpleListAdapter;
    List<String> resultList;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view =  inflater.inflate(R.layout.fragment_one_way, container, false);
        rvDestination=view.findViewById(R.id.autoDestintion);
        searchView = view.findViewById(R.id.autocompleteSource);
        edittext= view.findViewById(R.id.Birthday);
        eReminderTime=view.findViewById(R.id.timepicker);
        cablist=view.findViewById(R.id.cab_list);
        cablist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(),CabList.class);
                startActivity(intent);
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        eReminderTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int am  = Calendar.AM;
                TimePickerDialog mTimePicker;
                String amPmStr = "";
                if (mcurrentTime.get(Calendar.AM_PM) == Calendar.AM)
                    amPmStr = "AM";
                else if (mcurrentTime.get(Calendar.AM_PM) == Calendar.PM)
                    amPmStr = "PM";

                String finalAmPmStr = amPmStr;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        eReminderTime.setText( selectedHour + ":" + selectedMinute + ": " + finalAmPmStr);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the fields to specify which types of place data to
                // return after the user has made a selection.
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(requireActivity());


                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

        rvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(requireActivity());


                startActivityForResult(intent, AUTOCOMPLETE_DESTINATION_CODE);
            }
        });



        return view;

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

////    public void autoComplete(String input,SimpleListAdapter sAdapter, List<String> lList) {
////        List<String> arrayList = new ArrayList();
////
////
////        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
////        sb.append("input=" + input);
////        sb.append("&components=country:in");
////        sb.append("&key=" + getString(R.string.mapp_pi));
////
////        AndroidNetworking.get(sb.toString())
////                .setPriority(Priority.HIGH)
////                .build()
////                .getAsJSONObject(new JSONObjectRequestListener() {
////                    @Override
////                    public void onResponse(JSONObject response) {
////                        try {
////                            JSONObject jsonObject = new JSONObject(response.toString());
////                            JSONArray predictions = jsonObject.getJSONArray("predictions");
////                            for (int i = 0; i < predictions.length(); i++) {
////                                arrayList.add(predictions.getJSONObject(i).getString("description"));
////                                Log.d("PlacesApi :", String.valueOf(predictions.getJSONObject(i).getString("description")));
////                            }
////                            lList.addAll(arrayList);
////                            sAdapter.notifyDataSetChanged();
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////
////                    @Override
////                    public void onError(ANError anError) {
////                        Log.d("PlacesApi :", String.valueOf(anError));
////                    }
////                });
//
//
//       // return arrayList;
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (OneWayFragment.AUTOCOMPLETE_REQUEST_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                searchView.setText(""+place.getAddress());
                Log.i("Places", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("Places", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        else if (OneWayFragment.AUTOCOMPLETE_DESTINATION_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                rvDestination.setText(""+place.getAddress());
                Log.i("Places", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("Places", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }

    }
}