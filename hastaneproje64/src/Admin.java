import java.io.*;
import java.util.Scanner;

public class Admin {
    private static final String ADMIN_USERNAME = "22181616000";
    private static final String ADMIN_PASSWORD = "22181616000";

    public static boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Kullanıcı Adı: ");
                String username = scanner.nextLine();
                System.out.print("Şifre: ");
                String password = scanner.nextLine();

                if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                    System.out.println("Admin girişi başarılı. Hoş geldiniz, " + ADMIN_USERNAME + "!");
                    return true;
                } else {
                    System.out.println("Admin girişi başarısız. Tekrar denemek için 'q' tuşuna basın veya çıkış yapmak için 'exit' yazın.");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("exit")) {
                        System.out.println("Program kapatılıyor...");
                        System.exit(0);
                    } else if (choice.equalsIgnoreCase("q")) {
                        // Özel istisna fırlat
                        throw new InvalidChoiceException("Yeni giriş bilgilerini girin:");
                    } else {
                        // Geçersiz seçim durumu için özel istisna fırlat
                        throw new InvalidChoiceException("Geçersiz seçim.");
                    }
                }
            } catch (InvalidChoiceException e) {
                System.err.println(e.getMessage());
                // Döngü tekrar başa dönecek ve yeni giriş bilgilerini isteyecek
            }
        }
    }

    public static void performAdminOperations() {
        System.out.println("Admin Menüsü:");
        System.out.println("1. Doktor Maaşını Görüntüle");
        System.out.println("2. Doktor Maaşını Güncelle");
        System.out.println("3. Doktor Ekle");
        System.out.println("4. Doktor Sil");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Bir işlem seçin (1-3): ");
        while (true) {
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    displayDoctorSalary();
                    break;
                case 2:
                    updateDoctorSalary();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    deleteDoctor();
                    break;
                default:
                    System.out.println("Geçersiz seçenek.");
            }
            }

    }

    //Buradaki metodlar Statik yaptık çünkü nesne oluşturmadan bu metodları kullanmak istiyoruz
//    private static void addNewDoctor() {
//        // Yeni doktor eklemek için gerekli kodu buraya ekleyebilirsiniz
//        //System.out.println("Yeni doktor ekleme işlemi henüz gerçekleştirilmemiştir.");
//    }

//    private static void updateDoctorAvailability() {
//        // Doktorun müsaitlik durumunu güncellemek için gerekli kodu buraya ekleyebilirsiniz
//        System.out.println("Doktorun müsaitlik durumu güncelleme işlemi henüz gerçekleştirilmemiştir.");
//    }
    private static void deleteDoctor() {


        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Silmek istediğiniz doktorun adını girin:");
            String doctorNameToDelete = scanner.nextLine();

            File inputFile = new File("doctors_departments.txt");
            File tempFile = new File("doctors_departments_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean doctorFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String doctorName = parts[0].trim();

                if (!doctorName.equals(doctorNameToDelete)) {
                    writer.write(line + "\n");
                } else {
                    doctorFound = true;
                }
            }

            reader.close();
            writer.close();

            if (doctorFound) {
                if (!inputFile.delete()) {
                    System.out.println("Hata: Geçici dosya oluşturulurken bir hata oluştu.");
                    return;
                }

                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Hata: Dosya adı değiştirilirken bir hata oluştu.");
                } else {
                    System.out.println("Doktor silindi.");
                }
            } else {
                System.out.println("Hata: Silmek istediğiniz doktor bulunamadı.");
            }
        } catch (IOException e) {
            System.out.println("Doktor silinirken bir hata oluştu: " + e.getMessage());
        }
    }
    private static void addDoctor() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("doctors_departments.txt", true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Eklemek istediğiniz doktorun adını girin:");
            String doctorName = scanner.nextLine();
            System.out.println("Hangi bölümden olduğunu girin:");
            String department = scanner.nextLine();

            writer.write(department + ";" + doctorName +department + "\n");

            System.out.println("Doktor eklendi.");

        } catch (IOException e) {
            System.out.println("Doktor eklenirken bir hata oluştu: " + e.getMessage());
        }
    }
    private static void displayDoctorSalary() {
        try (BufferedReader reader = new BufferedReader(new FileReader("doctor_salary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Doktor maaşı görüntülenirken bir hata oluştu: " + e.getMessage());
        }
    }
    private static void updateDoctorSalary() {
        try (BufferedReader reader = new BufferedReader(new FileReader("doctor_salary.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("doctor_salary_temp.txt"))) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Güncellemek istediğiniz doktorun adını girin:");
            String targetDoctor = scanner.nextLine();
            System.out.println("Yeni maaşı girin:");
            double newSalary = scanner.nextDouble();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String doctorName = parts[0].trim();
                    double salary = Double.parseDouble(parts[1].trim());

                    if (doctorName.equals(targetDoctor)) {
                        line = doctorName + ": " + newSalary;
                    }
                }

                writer.write(line + "\n");
            }

            System.out.println("Doktor maaşı güncellendi.");

        } catch (IOException e) {
            System.out.println("Doktor maaşı güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }
}


