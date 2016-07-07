package database.thing;

/**
 * Created by Student on 7/07/2016.
 */
public class Commentary {
    private int commentaryId;
    private int userId;
    private int shopId;
    private int rating;

    public Commentary(){}

    public int getCommentaryId() { return commentaryId; }

    public void setCommentaryId(int commentaryId) {
        this.commentaryId = commentaryId;
    }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }


    public int getShopId() { return shopId; }

    public void setShopId(int shopId) { this.shopId = shopId; }


    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

}
