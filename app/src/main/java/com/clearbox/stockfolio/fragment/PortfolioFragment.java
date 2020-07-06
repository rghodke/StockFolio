package com.clearbox.stockfolio.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.adapter.MyStockItemRecyclerViewAdapter;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;
import com.clearbox.stockfolio.application.StockfolioApplication;

import com.clearbox.stockfolio.fragment.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class PortfolioFragment extends Fragment {

    private StockfolioViewModel mModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PortfolioFragment() {
        StockfolioApplication.getStockfolioComponent().inject(this);
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

        if (getActivity() != null)
            mModel = ViewModelProviders.of(getActivity()).get(StockfolioViewModel.class);

        mModel.updateHeldAssetData();
        setupViews(view);
        
        return view;
    }

    private void setupViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView_Portfolio);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyStockItemRecyclerViewAdapter(mModel.getHeldAssets()));
    }
}