public class Patient {
    private String name;
    private int age;
    private String patientID;
    private String additionalInfo;
    private Prescription[] prescriptions;

    public Patient(String name, int age, String patientID, String additionalInfo, Prescription[] prescriptions) {
        this.name = name;
        this.age = age;
        this.patientID = patientID;
        this.additionalInfo = additionalInfo;
        this.prescriptions = prescriptions;
    }

    // Diğer getter ve setter metotları buraya eklenebilir

    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;

    }

    public int getAge() {
        return age;
    }

}