package com.taishonet.retrofitsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private GitHubService gitHubService = RetrofitServiceFactory.provideGitHubService();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.sample_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestGitHub();
    }

    private void requestGitHub() {
        gitHubService.getUser("taisho6339")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User value) {
                        String text = new Gson().toJson(value);
                        Log.e("RESPONSE", text);
                        textView.setText(text);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("ERROR", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
