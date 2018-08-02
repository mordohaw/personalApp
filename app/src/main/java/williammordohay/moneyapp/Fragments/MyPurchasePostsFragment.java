package williammordohay.moneyapp.Fragments;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by William on 01/08/2018.
 */

public class MyPurchasePostsFragment extends PostListFragment {


    public MyPurchasePostsFragment(){}
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        /** My top posts ordonate by number of likes */
        String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("user-posts").child(myUserId)
                .orderByChild("buyDay");


        return myTopPostsQuery;
    }
}
