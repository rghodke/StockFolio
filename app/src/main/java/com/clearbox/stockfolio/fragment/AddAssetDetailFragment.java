package com.clearbox.stockfolio.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAssetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAssetDetailFragment extends Fragment {

    private StockfolioViewModel mModel;

    public AddAssetDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAssetDetailFragment newInstance() {
        AddAssetDetailFragment fragment = new AddAssetDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_asset_detail, container, false);

        setupViews(view);

        if (getActivity() != null)
            mModel = ViewModelProviders.of(getActivity()).get(StockfolioViewModel.class);

        if (mModel != null)
            System.out.println("mModel = " + mModel.getSelectedAsset().getValue().description);

        return view;
    }

    private void setupViews(View view) {
    }
}