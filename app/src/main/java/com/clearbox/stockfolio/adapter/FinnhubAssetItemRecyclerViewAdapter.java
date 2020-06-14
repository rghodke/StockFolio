package com.clearbox.stockfolio.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.fragment.dummy.DummyContent.DummyItem;
import com.clearbox.stockfolio.network.model.FinnhubAsset;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FinnhubAssetItemRecyclerViewAdapter extends RecyclerView.Adapter<FinnhubAssetItemRecyclerViewAdapter.ViewHolder> {

    private final List<FinnhubAsset> mAssets;

    public FinnhubAssetItemRecyclerViewAdapter(List<FinnhubAsset> items) {
        mAssets = items;
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
        holder.mIdView.setText(mAssets.get(position).symbol);
        holder.mContentView.setText(mAssets.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mAssets.size();
    }

    public void setData(List<FinnhubAsset> finnhubAssets) {
        mAssets.clear();
        mAssets.addAll(finnhubAssets);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public FinnhubAsset mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}