package williammordohay.moneyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;

import williammordohay.moneyapp.R;

public class MainActivity extends AppCompatActivity {

    public static int NB_JOURS_MAX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tutos-android-france.com/material-design-recyclerview-et-cardview


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_new_post);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void controlDate(){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int currentMonth = calendar.get(calendar.MONTH)+1;
        int currentDay = calendar.get(Calendar.DATE);

        switch (currentMonth){
            case 2 : NB_JOURS_MAX = 28;
            case 4 : case 6 : case 9 : case 11 : NB_JOURS_MAX = 30;
            default : NB_JOURS_MAX = 31;
        }

        //recup mois et montant mensuel en bdd
        int moisBdd=1, montantMensuel;
        if(currentMonth != moisBdd && moisBdd != 0){
            moisBdd = currentMonth;
            montantMensuel = 0;
            //effacer tous les posts dépense du mois
        }

        //Afficher le montant de la bdd

        //Ajouter le montant entré et l'augmenter au clic sur un bouton
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** onClick on delete restaurant button */
    /*public void cleanDatabase(View v){
        mDatabase.child("posts").setValue(null);
        mDatabase.child("user-posts").setValue(null);
        Toast.makeText(MainActivity.this, "votes clos !", Toast.LENGTH_LONG).show();
    }*/

    /** deconnect Action */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/
}
