
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lütfen bir seçenek seçin:");
        System.out.println("1. Hasta Girişi");
        System.out.println("2. Doktor Girişi");
        System.out.println("3. Admin Girişi");
//MsNurse.hemsireListele();
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Hasta girişi
                handlePatient();

                break;
            case 2:
                // Doktor girişi
                handleDoctor();

                break;
            case 3:
                // Admin girişi
                if (Admin.adminLogin()) {
                    Admin.performAdminOperations();
                } else {
                    System.out.println("Giriş başarısız. Lütfen kullanıcı adını ve şifreyi kontrol edip tekrar deneyin.");
                }
                break;

            default:
                System.out.println("Geçersiz seçenek. Programdan çıkılıyor.");
        }
    }

    private static void handlePatient() {
        // Hasta işlemleri


        // Dosyadan bölüm ve doktor bilgilerini okuma
        List<String> doctorAndDepartmentLines = readDoctorAndDepartmentFromFile("doctors_and_departments.txt");

        // Hasta oluşturma ve giriş yapma
        Patient patient = login();

        if (patient != null) {
            // Hastanın bölüm seçmesi
            String selectedDepartment = selectDepartment(doctorAndDepartmentLines);

            // Seçilen bölümdeki doktorları görüntüleme
            displayDoctors(doctorAndDepartmentLines, selectedDepartment);

            // Hastanın doktor seçmesi
            String selectedDoctor = selectDoctor(doctorAndDepartmentLines, selectedDepartment);
            System.out.println("Doktor seçildi: " + selectedDoctor);


            //buraya Nurse seçme işlemi de ekliyorum alt kısmı selectedNurse eklememiz lazım
            //ekledim orayı
            //seçilen hemşireyi müsaitolmaayna atmaamız lazım (dosya false olacak her seferinde
            // doysyı sıfırlayacak)
            ///ve müsait olmayanları yazmayacak

            //ayriyeten başka bir hasta randevu almak istiyor mu onu sormamız lazım


            //Hastanın Hemşireyi seçmesi
            MsNurse.hemsireListele();
            MsNurse.hemsireSecme();
            // Doktorun müsait olduğu saatleri ve günleri gösterme
            displayDoctorAvailability(selectedDoctor);
            // Hasta için randevu alma
            String selectedAppointment = selectAppointment();
            selectAppointment(patient, selectedDepartment, selectedDoctor, selectedAppointment);
        } else {
            System.out.println("Giriş başarısız. Lütfen tekrar deneyin.");
        }
    }

    private static Patient login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hasta Adı Soyadı: ");
        String patientName = scanner.nextLine();

        System.out.print("Hasta Yaşı: ");
        int patientAge = scanner.nextInt();

        // Diğer giriş bilgileri alınabilir

        // Hasta nesnesi oluşturuluyor
        Patient patient = new Patient(patientName, patientAge, "P001", "", new Prescription[]{});

        System.out.println("Giriş başarılı. Hoş geldiniz, " + patient.getName() + "!");
        return patient;
    }

    private static String selectDepartment(List<String> doctorAndDepartmentLines) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lütfen bir bölüm seçin:");

        try {
            // Tüm bölümleri ekrana yazdırma
            for (String line : doctorAndDepartmentLines) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    System.out.println(parts[0]); // Sadece bölüm adını yazdır
                }
            }

            // Kullanıcıdan bölüm seçmesini isteme
            System.out.print("Seçtiğiniz bölüm: ");
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    private static void displayDoctors(List<String> doctorAndDepartmentLines, String selectedDepartment) {
        System.out.println("\n" + selectedDepartment + " Bölümündeki Doktorlar:");

        // Seçilen bölümdeki doktorları ekrana yazdırma
        for (String line : doctorAndDepartmentLines) {
            String[] parts = line.split(";");
            if (parts.length == 2 && parts[0].equals(selectedDepartment)) {
                System.out.println("- " + parts[1]); // Seçilen bölümdeki doktor adını yazdır
            }
        }
    }

    private static String selectDoctor(List<String> doctorAndDepartmentLines, String selectedDepartment) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Lütfen bir doktor seçin: ");
            String selectedDoctor = scanner.nextLine();

            // Seçilen doktorun seçilen bölümde olup olmadığını kontrol etme
            boolean isValidDoctor = false;
            for (String line : doctorAndDepartmentLines) {
                String[] parts = line.split(";");
                if (parts.length == 2 && parts[0].equals(selectedDepartment) && parts[1].equals(selectedDoctor)) {
                    isValidDoctor = true;
                    break;
                }
            }

            // Eğer seçilen doktor geçerli değilse tekrar seçim yapma isteği
            while (!isValidDoctor) {
                System.out.print("Geçersiz doktor. Lütfen tekrar seçin: ");
                selectedDoctor = scanner.nextLine();

                for (String line : doctorAndDepartmentLines) {
                    String[] parts = line.split(";");
                    if (parts.length == 2 && parts[0].equals(selectedDepartment) && parts[1].equals(selectedDoctor)) {
                        isValidDoctor = true;
                        break;
                    }
                }
            }

            return selectedDoctor;
        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    private static void displayDoctorAvailability(String selectedDoctor) {
        // Bu bölümde, doktorun müsait olduğu saatleri ve günleri gösterme işlemini ekleyin.
        // Doktorun müsait olduğu saatleri ve günleri bir dosyadan okuyabilir ve ekrana yazdırabilirsiniz.
        // Örneğin:
        // Dosya formatı: "DoktorAdı;Gün;Saat"
        // Örnek dosya içeriği:
        // Doktor1;Pazartesi;10:00-12:00
        // Doktor1;Salı;14:00-16:00
        // ...

        try {
            List<String> doctorAvailabilityLines = readDoctorAvailabilityFromFile("doctor_availability.txt", selectedDoctor);

            if (doctorAvailabilityLines.isEmpty()) {
                System.out.println("Doktorun müsait olduğu bir zaman bulunamadı.");
            } else {
                System.out.println("\n" + selectedDoctor + " Doktorunun Müsait Olduğu Saatler ve Günler:");
                for (String line : doctorAvailabilityLines) {
                    System.out.println("- " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
    }

    private static List<String> readDoctorAvailabilityFromFile(String fileName, String selectedDoctor) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equals(selectedDoctor)) {
                    lines.add(parts[1] + " - " + parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
        return lines;
    }

    private static List<String> readDoctorAndDepartmentFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
        return lines;
    }

    private static String selectAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lütfen bir saat seçin (örneğin Pazartesi 10:00-12:00): ");
        return scanner.nextLine();
    }

    private static void selectAppointment(Patient patient, String selectedDepartment, String selectedDoctor, String selectedAppointment) {
        try {
            // Randevu bilgilerini dosyaya yazma işlemi
            String fileName = "appointments.txt";
            String appointmentInfo = patient.getName() + ";" + patient.getAge() + ";" +
                    patient.getPatientID() + ";" + selectedDepartment + ";" +
                    selectedDoctor + ";" + selectedAppointment+";"+MsNurse.selectedNurse;

            // Dosyaya yazma işlemi
            java.nio.file.Files.write(java.nio.file.Paths.get(fileName), (appointmentInfo + System.lineSeparator()).getBytes(), java.nio.file.StandardOpenOption.APPEND);

            System.out.println("Randevu alındı! Seçtiğiniz saat: " + selectedAppointment);
            System.out.println("Randevu bilgileri dosyaya yazıldı: " + fileName);
        } catch (IOException e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
        }
    }


    private static void handleDoctor() {
        // Doktor işlemleri
        Scanner scanner = new Scanner(System.in);

        System.out.println("Doktor adını girin:");
        String doctorName = scanner.nextLine();

        try {
            List<String> appointments = readAppointmentsFromFile();

            // Doktorun randevularını kontrol et
            checkAndDisplayAppointments(doctorName, appointments);

            // Güncelleme yapmak isterse
            System.out.println("Randevu güncellemek ister misiniz? (Evet/Hayır)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("evet")) {
                updateAppointments(doctorName, appointments);
                writeAppointmentsToFile(appointments);
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma veya yazma hatası: " + e.getMessage());
        }
    }

    private static void writeAppointmentsToFile(List<String> appointments) throws IOException {
        FileWriter writer = new FileWriter("appointments.txt");

        for (String appointment : appointments) {
            writer.write(appointment + "\n");
        }

        writer.close();
    }


    //Randevular text doyasının okur buradaki satırlıarı diziye atar
    private static List<String> readAppointmentsFromFile() throws IOException {
        List<String> appointments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"));

        String line;
        while ((line = reader.readLine()) != null) {
            appointments.add(line);
        }

        reader.close();
        return appointments;
    }



    //işlemi nasıl yapıyor?
    private static void checkAndDisplayAppointments(String doctorName, List<String> appointments) {
        System.out.println(doctorName + " için randevular:");

        for (String appointment : appointments) {
            String[] appointmentInfo = appointment.split(";");
            String doctor = appointmentInfo[4].trim();

            if (doctor.equals(doctorName)) {
                System.out.println("Hasta: " + appointmentInfo[0] + ", Tarih: " + appointmentInfo[5]);
            }
        }
    }


    //Hasta istediği saatte randevu alamaz seçilebilir randevu saatleri gösterilmeli ve seçim yapılmalı
    private static void updateAppointments(String doctorName, List<String> appointments) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Randevu güncellemek istediğiniz hastanın adını girin:");
        String patientName = scanner.nextLine();

        System.out.println("Yeni randevu tarihini girin:");
        String newAppointmentDate = scanner.nextLine();

        for (int i = 0; i < appointments.size(); i++) {
            String appointment = appointments.get(i);
            String[] appointmentInfo = appointment.split(";");
            String doctor = appointmentInfo[4].trim();
            String patient = appointmentInfo[0].trim();

            if (doctor.equals(doctorName) && patient.equals(patientName)) {
                // Güncelleme yap
                appointmentInfo[5] = newAppointmentDate;
                appointments.set(i, String.join(";", appointmentInfo));
                System.out.println("Randevu güncellendi.");
            }
        }
    }
//Buradan sonra kod yok

//    private static void handleAdmin() {
//        // Admin işlemleri
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Admin Menüsü:");
//        System.out.println("1. Doktor Maaşını Görüntüle");
//        System.out.println("2. Doktor Maaşını Güncelle");
//        System.out.println("3. Doktor Ekle");
//        System.out.println("4. Doktor Sil");
//
//        int adminChoice = scanner.nextInt();
//
//        switch (adminChoice) {
//            case 1:
//                displayDoctorSalary();
//                break;
//            case 2:
//                updateDoctorSalary();
//                break;
//            case 3:
//                addDoctor();
//                break;
//            case 4:
//                deleteDoctor();
//                break;
//            default:
//                System.out.println("Geçersiz seçenek.");
//        }
//    }

//    private static void displayDoctorSalary() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("doctor_salary.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Doktor maaşı görüntülenirken bir hata oluştu: " + e.getMessage());
//        }
//    }

//    private static void updateDoctorSalary() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("doctor_salary.txt"));
//             BufferedWriter writer = new BufferedWriter(new FileWriter("doctor_salary_temp.txt"))) {
//
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Güncellemek istediğiniz doktorun adını girin:");
//            String targetDoctor = scanner.nextLine();
//            System.out.println("Yeni maaşı girin:");
//            double newSalary = scanner.nextDouble();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(":");
//                if (parts.length == 2) {
//                    String doctorName = parts[0].trim();
//                    double salary = Double.parseDouble(parts[1].trim());
//
//                    if (doctorName.equals(targetDoctor)) {
//                        line = doctorName + ": " + newSalary;
//                    }
//                }
//
//                writer.write(line + "\n");
//            }
//
//            System.out.println("Doktor maaşı güncellendi.");
//
//        } catch (IOException e) {
//            System.out.println("Doktor maaşı güncellenirken bir hata oluştu: " + e.getMessage());
//        }
//    }
}


//    private static void addDoctor() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("doctors_departments.txt", true))) {
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Eklemek istediğiniz doktorun adını girin:");
//            String doctorName = scanner.nextLine();
//            System.out.println("Hangi bölümden olduğunu girin:");
//            String department = scanner.nextLine();
//
//            writer.write(department + ";" + doctorName +department + "\n");
//
//            System.out.println("Doktor eklendi.");
//
//        } catch (IOException e) {
//            System.out.println("Doktor eklenirken bir hata oluştu: " + e.getMessage());
//        }
//    }}

//    private static void deleteDoctor() {
//
//
//            try {
//                Scanner scanner = new Scanner(System.in);
//
//                System.out.println("Silmek istediğiniz doktorun adını girin:");
//                String doctorNameToDelete = scanner.nextLine();
//
//                File inputFile = new File("doctors_departments.txt");
//                File tempFile = new File("doctors_departments_temp.txt");
//
//                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//
//                String line;
//                boolean doctorFound = false;
//
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split(";");
//                    String doctorName = parts[0].trim();
//
//                    if (!doctorName.equals(doctorNameToDelete)) {
//                        writer.write(line + "\n");
//                    } else {
//                        doctorFound = true;
//                    }
//                }
//
//                reader.close();
//                writer.close();
//
//                if (doctorFound) {
//                    if (!inputFile.delete()) {
//                        System.out.println("Hata: Geçici dosya oluşturulurken bir hata oluştu.");
//                        return;
//                    }
//
//                    if (!tempFile.renameTo(inputFile)) {
//                        System.out.println("Hata: Dosya adı değiştirilirken bir hata oluştu.");
//                    } else {
//                        System.out.println("Doktor silindi.");
//                    }
//                } else {
//                    System.out.println("Hata: Silmek istediğiniz doktor bulunamadı.");
//                }
//            } catch (IOException e) {
//                System.out.println("Doktor silinirken bir hata oluştu: " + e.getMessage());
//            }
//        }
//
//    }










