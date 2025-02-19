package model;

public class NewsCategory {
    
    private int NewsCategoryID;
    private String NewsCategoryName;

    public NewsCategory() {
    }

    public NewsCategory(int NewsCategoryID, String NewsCategoryName) {
        this.NewsCategoryID = NewsCategoryID;
        this.NewsCategoryName = NewsCategoryName;
    }

    public int getNewsCategoryID() {
        return NewsCategoryID;
    }

    public void setNewsCategoryID(int NewsCategoryID) {
        this.NewsCategoryID = NewsCategoryID;
    }

    public String getNewsCategoryName() {
        return NewsCategoryName;
    }

    public void setNewsCategoryName(String NewsCategoryName) {
        this.NewsCategoryName = NewsCategoryName;
    }

}
