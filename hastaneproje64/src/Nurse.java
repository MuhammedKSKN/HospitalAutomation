import java.util.List;
import java.util.Scanner;

public class Nurse extends Staff {
    // Alanlar
    private String shift;
    private String supervisor;

    // Kurucu
    public Nurse(String name, int age, String id, String position, double salary, String shift, String supervisor) {
        super(name, age,  id, position, salary); // Üst sınıfın kurucusunu çağırır
        this.shift = shift;
        this.supervisor = supervisor;
    }

    // Yöntemler
    public String getShift() {
        return shift;
    }

    public String getSupervisor() {
        return supervisor;
    }


    //Hemşire seçme

//    private static String selectNurse(List<String> doctorAndDepartmentLines) {
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            System.out.print("Lütfen bir Hemşire seçin: ");
//            String selectedNurse = scanner.nextLine();
//
//            // Seçilen doktorun seçilen bölümde olup olmadığını kontrol etme
//            boolean isValidNurse = false;
//            for (String line : doctorAndDepartmentLines) {
//                String[] parts = line.split(";");
//                if (parts.length == 2 && parts[0].equals(selectedDepartment) && parts[1].equals(selectedDoctor)) {
//                    isValidNurse = true;
//                    break;
//                }
//            }
//
//            // Eğer seçilen doktor geçerli değilse tekrar seçim yapma isteği
//            while (!isValidDoctor) {
//                System.out.print("Geçersiz doktor. Lütfen tekrar seçin: ");
//                selectedDoctor = scanner.nextLine();
//
//                for (String line : doctorAndDepartmentLines) {
//                    String[] parts = line.split(";");
//                    if (parts.length == 2 && parts[0].equals(selectedDepartment) && parts[1].equals(selectedDoctor)) {
//                        isValidDoctor = true;
//                        break;
//                    }
//                }
//            }
//
//            return selectedDoctor;
//        } catch (Exception e) {
//            System.out.println("Bir hata oluştu: " + e.getMessage());
//            return null;
//        }
//    }
}
