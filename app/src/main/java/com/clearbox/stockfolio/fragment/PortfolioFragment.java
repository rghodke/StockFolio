package com.clearbox.stockfolio.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.adapter.MyStockItemRecyclerViewAdapter;

import fragment.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class PortfolioFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PortfolioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PortfolioFragment newInstance() {
        PortfolioFragment fragment = new PortfolioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        setupViews(view);

        return view;
    }

    private void setupViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView_Portfolio);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyStockItemRecyclerViewAdapter(DummyContent.ITEMS));
    }
}