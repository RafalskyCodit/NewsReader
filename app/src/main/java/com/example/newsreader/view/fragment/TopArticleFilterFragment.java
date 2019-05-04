package com.example.newsreader.view.fragment;


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

import com.example.newsreader.R;
import com.hbb20.CountryCodePicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopArticleFilterFragment extends Fragment {
    private EditText queryEdit;
    private TextInputLayout queryInput;
    private Spinner categorySpinner;
    private CountryCodePicker countryPicker;
    private Button searchButton;

    public TopArticleFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListeners();
    }

    private void setListeners() {
        searchButton.setOnClickListener(view -> {

        });

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

    private void init(View view) {
        queryEdit = view.findViewById(R.id.query_edit);
        queryInput = view.findViewById(R.id.query_input);
        queryInput.setError(getResources().getString(R.string.empty_field_error));
        categorySpinner = view.findViewById(R.id.category_spinner);
        countryPicker = view.findViewById(R.id.country_picker);
        searchButton = view.findViewById(R.id.search_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.article_category));
        categorySpinner.setAdapter(adapter);
    }
}
