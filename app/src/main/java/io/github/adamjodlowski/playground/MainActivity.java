package io.github.adamjodlowski.playground;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextInputLayout emailInput = (TextInputLayout) findViewById(R.id.input_email);
        emailInput.setCounterEnabled(true);
        emailInput.setCounterMaxLength(20);
        emailInput.setErrorEnabled(true);
        emailInput.setError("Email not provided");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action1:
                Log.d("PLAYGROUND", "Action 1 selected");
                return true;
            case R.id.action2:
                Log.d("PLAYGROUND", "Action 2 selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
