package williammordohay.moneyapp.Fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by William on 01/08/2018.
 */

public class PurchasePostsFragment extends PostListFragment {

    public PurchasePostsFragment(){}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        /** ordonate by best votes and limit to 20 purchases */
        Query recentPostsQuery = databaseReference.child("posts").orderByChild("buyDay").limitToFirst(30);

        return recentPostsQuery;
    }
}
