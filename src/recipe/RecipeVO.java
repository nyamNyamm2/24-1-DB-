package recipe;

import java.io.Serializable;

public class RecipeVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String title;
    private int id;
    private String content;
    private String diff;

    public RecipeVO() {};

    public RecipeVO(String title, int id, String content, String diff)
    {
        this.title = title;
        this.id = id;
        this.content = content;
        this.diff = diff;
    }

    public String getRecipeTitle() {
        return title;
    }
    public void setRecipeTitle(String title) {
        this.title = title;
    }
    public int getRecipeId() {
        return id;
    }
    public void setRecipeId(int id) {
        this.id = id;
    }
    public String getRecipeContent() {
        return content;
    }
    public void setRecipeContent(String content) {
        this.content = content;
    }
    public String getRecipeDiff() {
        return diff;
    }
    public void setRecipeDiff(String diff) {
        this.diff = diff;
    }
}
