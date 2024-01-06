import java.util.List;
import java.util.ArrayList;

public class Doctor extends Staff {
    // Alanlar
    private String specialization;
    private List<Appointment> appointments;

    // Kurucu
    // Kurucu
    // Doctor sınıfındaki kurucu metod güncelleniyor
    public Doctor(String name, int age,  String id, String position, double salary, String specialization, Appointment[] appointments) {
        super(name, age,  id, position, salary); // Staff sınıfının kurucusunu çağırır
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
    }



    // Yöntemler
    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Yeni randevu eklemek için metod
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
