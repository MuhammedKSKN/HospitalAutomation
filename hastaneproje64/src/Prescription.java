public class Prescription {
    // Alanlar
    private String id;
    private String medicine;
    private String dosage;
    private String duration;

    // Kurucu
    public Prescription(String id, String medicine, String dosage, String duration) {
        this.id = id;
        this.medicine = medicine;
        this.dosage = dosage;
        this.duration = duration;
    }

    // Yöntemler
    public String getId() {
        return id;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDuration() {
        return duration;
    }
}
