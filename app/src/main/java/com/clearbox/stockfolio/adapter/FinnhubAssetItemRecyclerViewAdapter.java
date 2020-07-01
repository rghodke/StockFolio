package com.clearbox.stockfolio.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.fragment.AddAssetFragment;
import com.clearbox.stockfolio.network.model.FinnhubAsset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinnhubAssetItemRecyclerViewAdapter extends RecyclerView.Adapter<FinnhubAssetItemRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<FinnhubAsset> mInitalAssets, mAssets;
    private AddAssetFragment.AddAssetFragmentInteractionListener mListener;

    public FinnhubAssetItemRecyclerViewAdapter(List<FinnhubAsset> items, AddAssetFragment.AddAssetFragmentInteractionListener listener) {
        mInitalAssets = items;
        mAssets = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_finnhub_asset, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mAssets.get(position);
        holder.mTextViewStockSymbol.setText(mAssets.get(position).symbol);
        holder.mTextViewStockDescription.setText(mAssets.get(position).description);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFinnhubAssetListItemClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssets.size();
    }

    public void setData(List<FinnhubAsset> finnhubAssets) {
        mInitalAssets.clear();
        mAssets.clear();
        mAssets.addAll(finnhubAssets);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<FinnhubAsset> FilteredArrayNames = new ArrayList<FinnhubAsset>();

                // perform your search here using the searchConstraint String.
                constraint = constraint.toString().toLowerCase();

                for (int i = 0; i < mInitalAssets.size(); i++) {
                    String stockSymbol = mInitalAssets.get(i).symbol;
                    if (stockSymbol.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        FilteredArrayNames.add(mInitalAssets.get(i));
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mAssets = (List<FinnhubAsset>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTextViewStockSymbol;
        public final TextView mTextViewStockDescription;
        public FinnhubAsset mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextViewStockSymbol = (TextView) view.findViewById(R.id.TextView_Stock_Symbol);
            mTextViewStockDescription = (TextView) view.findViewById(R.id.TextView_Stock_Description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextViewStockDescription.getText() + "'";
        }
    }
}