package com.example.newsreader.view.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newsreader.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFilterFragment extends Fragment {
    private EditText queryEdit;
    private TextInputLayout queryInput;
    private Spinner languageSpinner;
    private Spinner sortSpinner;
    private TextView fromDateInfo;
    private TextView toDateInfo;
    private TextView fromTimeInfo;
    private TextView toTimeInfo;
    private Button searchButton;
    private Button fromDateButton;
    private Button fromTimeButton;
    private Button toDateButton;
    private Button toTimeButton;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());

    private Calendar fromCalendar = Calendar.getInstance();
    private Calendar toCalendar = Calendar.getInstance();

    public GeneralFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setInitialTimeAndDate();
        setListeners();
    }

    private void setListeners() {
        DatePickerDialog.OnDateSetListener fromDateListener = (picker, year, month, dayOfMonth) -> {
            fromCalendar.set(year, month, dayOfMonth);
            fromDateInfo.setText(dateFormat.format(fromCalendar.getTime()));
        };

        TimePickerDialog.OnTimeSetListener fromTimeListener = (picker, hourOfDay, minute) -> {
            fromCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            fromCalendar.set(Calendar.MINUTE, minute);
            fromTimeInfo.setText(timeFormat.format(fromCalendar.getTime()));
        };

        DatePickerDialog.OnDateSetListener toDateListener = (picker, year, month, dayOfMonth) -> {
            toCalendar.set(year, month, dayOfMonth);
            toDateInfo.setText(dateFormat.format(toCalendar.getTime()));
        };

        TimePickerDialog.OnTimeSetListener toTimeListener = (picker, hourOfDay, minute) -> {
            toCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            toCalendar.set(Calendar.MINUTE, minute);
            toTimeInfo.setText(timeFormat.format(toCalendar.getTime()));
        };

        fromDateButton.setOnClickListener(view -> new DatePickerDialog(view.getContext(), fromDateListener,
                fromCalendar.get(Calendar.YEAR),
                fromCalendar.get(Calendar.MONTH),
                fromCalendar.get(Calendar.DAY_OF_MONTH)).show());

        fromTimeButton.setOnClickListener(view -> new TimePickerDialog(view.getContext(), fromTimeListener,
                fromCalendar.get(Calendar.HOUR_OF_DAY),
                fromCalendar.get(Calendar.MINUTE), true).show());

        toDateButton.setOnClickListener(view -> new DatePickerDialog(view.getContext(), toDateListener,
                toCalendar.get(Calendar.YEAR),
                toCalendar.get(Calendar.MONTH),
                toCalendar.get(Calendar.DAY_OF_MONTH)).show());

        toTimeButton.setOnClickListener(view -> new TimePickerDialog(view.getContext(), toTimeListener,
                toCalendar.get(Calendar.HOUR_OF_DAY),
                toCalendar.get(Calendar.MINUTE), true).show());

        queryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    queryInput.setErrorEnabled(true);
                    queryInput.setError(getResources().getString(R.string.empty_field_error));
                }else{
                    queryInput.setErrorEnabled(false);
                }
            }
        });
    }

    private void setInitialTimeAndDate() {
        fromDateInfo.setText(dateFormat.format(fromCalendar.getTime()));
        toDateInfo.setText(dateFormat.format(fromCalendar.getTime()));
        fromTimeInfo.setText(timeFormat.format(fromCalendar.getTime()));
        toTimeInfo.setText(timeFormat.format(fromCalendar.getTime()));
    }

    private void init(View view) {
        queryEdit = view.findViewById(R.id.query_edit);
        queryInput = view.findViewById(R.id.query_input);
        queryInput.setError(getResources().getString(R.string.empty_field_error));
        languageSpinner = view.findViewById(R.id.language_spinner);
        sortSpinner = view.findViewById(R.id.sort_spinner);

        fromDateInfo = view.findViewById(R.id.from_date_info);
        fromTimeInfo = view.findViewById(R.id.from_time_info);
        toDateInfo = view.findViewById(R.id.to_date_info);
        toTimeInfo = view.findViewById(R.id.to_time_info);

        searchButton = view.findViewById(R.id.search_button);
        fromTimeButton = view.findViewById(R.id.from_time_button);
        fromDateButton = view.findViewById(R.id.from_date_button);
        toDateButton = view.findViewById(R.id.to_date_button);
        toTimeButton = view.findViewById(R.id.to_time_button);

        String[] languages = getResources().getStringArray(R.array.article_language);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                languages);
        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setSelection(languages.length - 1);

        String[] sorting = getResources().getStringArray(R.array.article_sorting);
        ArrayAdapter<String> sortingAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                sorting);
        sortSpinner.setAdapter(sortingAdapter);
        sortSpinner.setSelection(sorting.length - 1);
    }
}
