public class Department {
    // Alanlar
    private String name;
    private String description;
    private Staff[] staff;

    // Kurucu
    public Department(String name, String description, Staff[] staff) {
        this.name = name;
        this.description = description;
        this.staff = staff;
    }

    // Yöntemler
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Staff[] getStaff() {
        return staff;
    }
}
