package williammordohay.moneyapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import williammordohay.moneyapp.Model.Purchase;
import williammordohay.moneyapp.R;

/**
 * Created by William on 01/08/2018.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView priceView;
    public TextView authorView;;
    public TextView supermarketView;
    public TextView dateView;
    private final static String TAG =  "PostViewHolder";


    public PostViewHolder(View itemView) {
        super(itemView);

        priceView = itemView.findViewById(R.id.cost);
        authorView = itemView.findViewById(R.id.post_author);
        supermarketView = itemView.findViewById(R.id.category);
        dateView = itemView.findViewById(R.id.buying_date);
    }

    public void bindToPost(Purchase post) {
        Log.d(TAG, "bindToPost() called with: post = [" + post + "]");
        priceView.setText(Float.toString(post.price));
        dateView.setText(post.buyDay+"/"+post.buyMonth);
        authorView.setText(post.author);
        supermarketView.setText(post.category);
    }
}
