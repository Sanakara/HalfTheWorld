package database.thing;

/**
 * Created by Student on 7/07/2016.
 */
public class Shop {
    private int shopId;
    private String shopName;
    private String location;
    private int rating;

    public Shop(){}

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }


    public int getShopId() { return shopId; }

    public void setShopId(int shopId) { this.shopId = shopId; }


    public String getShopName() { return shopName; }

    public void setShopName(String shopName) { this.shopName = shopName; }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }
}
