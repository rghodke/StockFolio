package com.clearbox.stockfolio.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clearbox.stockfolio.R;

import fragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStockItemRecyclerViewAdapter extends RecyclerView.Adapter<MyStockItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MyStockItemRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_portfolio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTextViewStockName.setText(mValues.get(position).id);
        holder.mTextViewCurrentPrice.setText(mValues.get(position).content);
        holder.mTextViewPriceDelta.setText(mValues.get(position).content);
        holder.mTextViewHoldingsPrice.setText(mValues.get(position).content);
        holder.mTextViewHoldingsAmount.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTextViewStockName;
        public final TextView mTextViewCurrentPrice;
        public final TextView mTextViewPriceDelta;
        public final TextView mTextViewHoldingsPrice;
        public final TextView mTextViewHoldingsAmount;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextViewStockName = (TextView) view.findViewById(R.id.TextView_stock_name);
            mTextViewCurrentPrice = (TextView) view.findViewById(R.id.TextView_stock_price);
            mTextViewPriceDelta = (TextView) view.findViewById(R.id.TextView_stock_price_delta);
            mTextViewHoldingsPrice = (TextView) view.findViewById(R.id.TextView_holdings_price);
            mTextViewHoldingsAmount = (TextView) view.findViewById(R.id.TextView_holdings_amount);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextViewStockName.getText() + "'";
        }
    }
}