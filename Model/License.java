package Model;
import java.time.LocalDate;

public class License {

    String licenseId;
    boolean validForOneYear;
    LocalDate issueDate;
    LocalDate expirationDate;
    Category category;

    public License(String licenseId, boolean validForOneYear, LocalDate issueDate, LocalDate expirationDate, Category category) {
        this.licenseId = licenseId;
        this.validForOneYear = validForOneYear;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public boolean isValid() {
        return !LocalDate.now().isAfter(this.expirationDate);
    }

    public String getLicenseId() {
        return this.licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public boolean getValidForOneYear() {
        return this.validForOneYear;
    }

    public void setValidForOneYear(boolean validForOneYear) {
        this.validForOneYear = validForOneYear;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
