public class Hospital {
    // Alanlar
    private String name;
    private String address;
    private Department[] departments;

    // Kurucu
    public Hospital(String name, String address, Department[] departments) {
        this.name = name;
        this.address = address;
        this.departments = departments;
    }

    // Yöntemler
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Department[] getDepartments() {
        return departments;
    }
}
