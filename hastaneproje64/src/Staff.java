public class Staff extends Person {
    // Alanlar
    private String id;
    private String position;
    private double salary;

    // Kurucu

    // Staff sınıfındaki kurucu metod güncelleniyor
    public Staff(String name, int age, String id, String position, double salary) {
        super(name,age);


        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    public Staff(String name, int age, String[] hobbies) {
        super(name, age);
    }

    // Yöntemler
    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}
