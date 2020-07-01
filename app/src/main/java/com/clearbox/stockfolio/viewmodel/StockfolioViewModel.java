package com.clearbox.stockfolio.viewmodel;

import android.icu.util.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetCandleData;
import com.clearbox.stockfolio.network.model.FinnhubAssetStockData;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class StockfolioViewModel extends ViewModel {

    @Inject
    FinnhubApiClient mFinnhubApiClient;

    private MutableLiveData<List<FinnhubAsset>> mFinnhubAssets;
    private final MutableLiveData<FinnhubAsset> mSelectedFinnhubAsset = new MutableLiveData<>();
    private final MutableLiveData<FinnhubAssetStockData> mFinnhubAssetStockData = new MutableLiveData<>();
    private final MutableLiveData<FinnhubAssetCandleData> mFinnhubAssetCandleData = new MutableLiveData<>();
//    private final MutableLiveData<GitHubIssue> mSelectedIssue = new MutableLiveData<>();
//    private MutableLiveData<List<GitHubIssue>> mIssues;

    public StockfolioViewModel() {
        StockfolioApplication.getStockfolioComponent().inject(this);
    }

    public LiveData<List<FinnhubAsset>> getAssets() {
        if (mFinnhubAssets == null) {
            mFinnhubAssets = new MutableLiveData<>();
            loadAssets();
        }
        return mFinnhubAssets;
    }

    private void loadAssets() {
        mFinnhubApiClient.loadAssets("US").subscribe(new Subscriber<List<FinnhubAsset>>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<FinnhubAsset> s) { mFinnhubAssets.setValue(s); }
        });
    }

    public void onFinnhubAssetSelected(FinnhubAsset item) {
        mSelectedFinnhubAsset.setValue(item);
    }

    public LiveData<FinnhubAsset> getSelectedAsset() {
        return mSelectedFinnhubAsset;
    }

    public LiveData<FinnhubAssetCandleData> getFinnhubStockData() {
        if (getSelectedAsset() != null) {
            if (getSelectedAsset().getValue() != null){
                loadFinnhubAssetStockData(getSelectedAsset().getValue());
            }
        }
        return mFinnhubAssetCandleData;
    }

    private void loadFinnhubAssetStockData(FinnhubAsset finnhubAsset) {
        mFinnhubApiClient.loadStockData(finnhubAsset.symbol).subscribe(new Subscriber<FinnhubAssetStockData>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(FinnhubAssetStockData s) { mFinnhubAssetStockData.setValue(s); }
        });
    }

    public LiveData<FinnhubAssetCandleData> getFinnhubAssetCandleData(int timeWindow) {
        if (getSelectedAsset() != null) {
            if (getSelectedAsset().getValue() != null){
                loadFinnhubAssetCandleData(getSelectedAsset().getValue(), timeWindow);
            }
        }
        return mFinnhubAssetCandleData;
    }

    long ONE_DAY_IN_MILLIS = 86400000L;

    private void loadFinnhubAssetCandleData(FinnhubAsset finnhubAsset, int timeWindow) {
        //TODO: Get resolution from spinner
        long currentUnixTime = System.currentTimeMillis()/1000L;
        long fromUnixTime = (System.currentTimeMillis() - ONE_DAY_IN_MILLIS)/1000L; //Default to 1D
        String resolution = "30"; //default to 30 mins
        switch (timeWindow) {
            case 0: //1H
                resolution = "5";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS/24))/1000L; //Divide by 24 for 1 hr
                break;
            case 1: //12H
                resolution = "15";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS/2))/1000L; //Divide by 2 for 12 hr
                break;
            case 2: //1D
                resolution = "30";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS/1))/1000L; //Divide by 1 for 24 hr
                break;
            case 3: //3D
                resolution = "60";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS * 3))/1000L; //Multiply by 3 for 3 days
                break;
            case 4: //1W
                resolution = "D";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS * 7))/1000L; //Multiply by 7 for 7 days
                break;
            case 5: //1M
                resolution = "D";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS * 30))/1000L; //Multiply by 30 for 30 days
                break;
            case 6: //3M
                resolution = "W";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS * 90))/1000L; //Multiply by 90 for 90 days
                break;
            case 7: //6M
                resolution = "W";
                fromUnixTime = (System.currentTimeMillis() - (ONE_DAY_IN_MILLIS * 180))/1000L; //Multiply by 180 for 180 days
                break;
            default:
                break;
        }

        mFinnhubApiClient.loadCandleData(finnhubAsset.symbol, resolution, String.valueOf(fromUnixTime), String.valueOf(currentUnixTime)).subscribe(new Subscriber<FinnhubAssetCandleData>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(FinnhubAssetCandleData s) { mFinnhubAssetCandleData.setValue(s); }
        });
    }

    public LiveData<FinnhubAssetCandleData> getFinnhubAssetDetailSelected() {
        return mFinnhubAssetCandleData;
    }

//    public LiveData<List<GitHubIssue>> getRepos() {
//        if (mRepos == null) {
//            mRepos = new MutableLiveData<>();
//            loadRepos();
//        }
//        return mRepos;
//    }
//
//    public LiveData<List<GitHubIssue>> getIssues() {
//        if (mIssues == null) {
//            mIssues = new MutableLiveData<>();
//            loadIssues(getSelected());
//        }
//        return mIssues;
//    }
//
//    private void loadRepos() {
//        mGitHubApiClient.getRepos("intuit").subscribe(new Subscriber<List<GitHubRepository>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(List<GitHubRepository> s) {
//                mRepos.setValue(s);
//            }
//        });
//
//    }
//
//    public void onRepoSelected(GitHubRepository item) {
//        mSelectedRepo.setValue(item);
//    }
//
//    public LiveData<GitHubRepository> getSelected() {
//        return mSelectedRepo;
//    }
//
//    private void loadIssues(LiveData<GitHubRepository> mSelectedRepo) {
//        if (mSelectedRepo.getValue() != null) {
//            mGitHubApiClient.getIssues("intuit", mSelectedRepo.getValue().getName()).subscribe(new Subscriber<List<GitHubIssue>>() {
//                @Override
//                public void onCompleted() {
//
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onNext(List<GitHubIssue> s) {
//                    mIssues.setValue(s);
//                }
//            });
//        }
//    }
//
//    public void onIssueSelected(GitHubIssue item) {
//        mSelectedIssue.setValue(item);
//    }

}
