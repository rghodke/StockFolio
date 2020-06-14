package com.clearbox.stockfolio.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetListResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class StockfolioViewModel extends ViewModel {

    @Inject
    FinnhubApiClient mFinnhubApiClient;

    private final MutableLiveData<FinnhubAsset> mSelectedFinnhubAsset = new MutableLiveData<>();
    private MutableLiveData<List<FinnhubAsset>> mFinnhubAssets;
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

    public void onRepoSelected(FinnhubAsset item) {
        mSelectedFinnhubAsset.setValue(item);
    }

    public LiveData<FinnhubAsset> getSelected() {
        return mSelectedFinnhubAsset;
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
