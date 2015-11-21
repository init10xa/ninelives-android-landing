package com.rootcave.ninelive.ninelives;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "Register Activity";
    @Bind(R.id.input_name)EditText _input_name;
    @Bind(R.id.input_mobile)EditText _input_mobile;
    @Bind(R.id.input_email) EditText _input_email;
    @Bind(R.id.input_discount_code) EditText _input_discount_code;
    @Bind(R.id.bRegister) Button _bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        _bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               register();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void register(){
        Log.d(TAG, "Register");

        if (!validate()) {
            onReisterFailed();
            return;
        }
        _bRegister.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this,
                R.style.Material_App_Dialog_Light);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registring Account...");
        progressDialog.show();

        String name = _input_name.getText().toString();
        String email = _input_email.getText().toString();
        String discountCode=_input_discount_code.getText().toString();
        String mobile=_input_mobile.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onRegistrationSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onReisterFailed() {
        Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_LONG).show();

        _bRegister.setEnabled(true);
    }


    public void onRegistrationSuccess() {
        _bRegister.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }


    public boolean validate() {
        boolean valid = true;

        String name = _input_name.getText().toString();
        String email = _input_email.getText().toString();
        String discountCode=_input_discount_code.getText().toString();
        String mobile=_input_mobile.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _input_name.setError("at least 3 characters");
            valid = false;
        } else {
            _input_name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _input_email.setError("enter a valid email address");
            valid = false;
        } else {
            _input_email.setError(null);
        }

        if (discountCode.isEmpty() || discountCode.length() < 4 || discountCode.length() > 10) {
            _input_discount_code.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _input_discount_code.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 11 ) {
            _input_mobile.setError("Please Enter Elevent Digits");
            valid = false;
        } else {
            _input_mobile.setError(null);
        }

        return valid;
    }

}
