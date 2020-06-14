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
        holder.mTextViewStockSymbol.setText(mAssets.get(position).symbol);
        holder.mTextViewStockDescription.setText(mAssets.get(position).description);
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