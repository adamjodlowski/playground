package io.github.adamjodlowski.playground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integerObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("PLAYGROUND", "call: " + integer);
            }
        });

    }

    Observable<Integer> integerObservable = Observable.just(4, 8, 15, 16, 23, 42);

    Observer<Integer> integerObserver = new Observer<Integer>() {
        @Override
        public void onCompleted() {
            Log.d("PLAYGROUND", "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d("PLAYGROUND", "onError", e);
        }

        @Override
        public void onNext(Integer integer) {
            Log.d("PLAYGROUND", "onNext: " + integer);
        }
    };

}
