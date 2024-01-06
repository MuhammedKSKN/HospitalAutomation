public class Appointments {
    private String doctor;
    private String time;

    public Appointments(String doctor, String time) {
        this.doctor = doctor;
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getTime() {
        return time;
    }
}
