package com.ruth.android.whoisapp.fragments;

/**
 * Created by RUTH on 15/05/08.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ruth.android.whoisapp.R;
import com.ruth.android.whoisapp.tasks.SearchTask;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlaceholderFragment extends Fragment {

    private static final String LOGGER = PlaceholderFragment.class.getSimpleName();

    private View rootView = null;
    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View arg)
        {
            operation();
        }
    };

    public PlaceholderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(onClickListener);
        return rootView;
    }

    /********************
     * Method to call services
     * @return
     ********************/
    public void operation()
    {
        EditText txtSearch=(EditText) rootView.findViewById(R.id.txtSearch);
        Log.i(LOGGER, "************" + txtSearch.getText().toString());
        SearchTask searchTask = new SearchTask();
        searchTask.execute("google.com");
    }
}
