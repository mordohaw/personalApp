package williammordohay.moneyapp.Model;

import com.google.firebase.database.Exclude;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by William on 01/08/2018.
 */

public class Purchase {
    public String supermarket, author, uid;
    public float price;
    public int buyMonth, buyDay;

    public Purchase() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Purchase(String uid, String author, String supermarket, float price) {
        this.uid = uid;
        this.supermarket = supermarket;
        this.price = price;
        this.author = author;

        //set buying's date
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        this.buyMonth = calendar.get(calendar.MONTH)+1;
        this.buyDay = calendar.get(Calendar.DATE);
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("buyDay", buyDay);
        result.put("buyMonth", buyMonth);
        result.put("supermarket", supermarket);
        result.put("price", price);

        return result;
    }

}
