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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        computeNumbersObservable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integerObserver);

    }

    Observer<Integer> integerObserver = new Observer<Integer>() {
        @Override
        public void onCompleted() {
            Log.d("PLAYGROUND", "onCompleted");
            textView.setText("stream completed");
        }

        @Override
        public void onError(Throwable e) {
            Log.d("PLAYGROUND", "onError", e);
        }

        @Override
        public void onNext(Integer integer) {
            Log.d("PLAYGROUND", "onNext: " + integer);
            textView.setText(integer.toString());
        }
    };

    Observable<Integer> computeNumbersObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
        @Override
        public void call(Subscriber<? super Integer> subscriber) {

            int i = 0;

            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    subscriber.onError(e); // report error
                }

                subscriber.onNext(i++); // emit data

                if (i == 10) {
                    break;
                }

            }

            subscriber.onCompleted(); // indicate stream completion
        }
    });

}
