package search;

import java.io.Serializable;

public class SearchVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String title;
    private int id;
    private String date;

    public SearchVO() {};

    public SearchVO(String title, int id, String date)
    {
        this.title = title;
        this.id = id;
        this.date = date;
    }

    public String getSearchTitle() {
        return title;
    }
    public void setSearchTitle(String title) {
        this.title = title;
    }
    public int getSearchId() {
        return id;
    }
    public void setSearachId(int id) {
        this.id = id;
    }
    public String getSearchDate() {
        return date;
    }
    public void setSearchDate(String date) {
        this.date = date;
    }
}
