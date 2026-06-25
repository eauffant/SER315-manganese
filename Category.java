public class Category {

    String categoryLevel;
    String description;

    public Category(String categoryLevel, String description) {
        this.categoryLevel = categoryLevel;
        this.description = description;
    }

    public String getCategoryLevel() {
        return this.categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
