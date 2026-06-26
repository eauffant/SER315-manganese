public class Category {

    int categoryLevel;
    String description;

    public Category(int categoryLevel, String description) {
        this.categoryLevel = categoryLevel;
        this.description = description;
    }

    public int getCategoryLevel() {
        return this.categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
