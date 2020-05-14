package com.hansung.android.homework2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static android.content.Intent.getIntent;

/**
 * A simple {@link Fragment} subclass.
 */
//모든것은 프래그먼트에서 처리
public class TitlesFragment extends Fragment {
    final static String TAG="SQLITEDBTEST";
    //RestaurantDetail액티비티 위에서 동작할 TitlesFragment(fragment_titles.xml)
    public TitlesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflator를 사용한 rootView는 프래그먼트에서 화면을 부풀리는 중요한 역할
        View rootView = inflater.inflate(R.layout.fragment_titles, container, false);
        return rootView;
    }
}