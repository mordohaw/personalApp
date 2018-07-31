package williammordohay.moneyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import williammordohay.moneyapp.Model.User;
import williammordohay.moneyapp.R;

public class LoginActivity extends BaseActivity implements  View.OnClickListener {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    //citizenfood38 mdp Toto1234
    private static final String TAG = "LoginActivity";


    private EditText usernameField;
    private EditText passwordField;
    private Button signInButton, registerLinkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** init firebase components */
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        /** init views */
        usernameField = findViewById(R.id.loginUsername);
        passwordField = findViewById(R.id.loginPassword);
        signInButton = findViewById(R.id.loginButton);
        registerLinkBtn = findViewById(R.id.registerLinkButton);

        effetAuClic(signInButton);
        effetAuClic(registerLinkBtn);

        signInButton.setOnClickListener(this);
        registerLinkBtn.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        //check the fields and pass over if not empty
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        //get values from fields (email and password)
        String email = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        //sign in in the database
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            //case auth successfull
                            onAuthSuccess(task.getResult().getUser());
                            Toast.makeText(LoginActivity.this, "Sign In Successfuly done",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //case auth unsuccessfull
                            Toast.makeText(LoginActivity.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        //writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        //get the username in the email's string
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    // [START basic_write]
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
    // [END basic_write]

    private boolean validateForm() {
        //check if the fields are empty and display red alert if empty
        boolean result = true;
        if (TextUtils.isEmpty(usernameField.getText().toString())) {
            usernameField.setError("Required");
            result = false;
        } else {
            usernameField.setError(null);
        }

        if (TextUtils.isEmpty(passwordField.getText().toString())) {
            passwordField.setError("Required");
            result = false;
        } else {
            passwordField.setError(null);
        }

        return result;
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.loginButton) {
            signIn();
        } else if (i == R.id.registerLinkButton) {
            Intent communicationIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            // 04/12 passPosition(communicationIntent);
            //effetAuClic(v);

            startActivity(communicationIntent);
        }
    }
}
