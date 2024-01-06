import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MsNurse extends Staff {
    static String selectedNurse;
    public MsNurse(String name, int age, String id, String position, double salary) {
        super(name, age, id, position, salary);
    }

    //Hemşirelerin ismini yazdır
    public static void hemsireListele() {
        String dosyaAdi = "Nurses.txt";
        String satir;
        System.out.println("Müsait olan hemşireler:\n");
        try {
            BufferedReader oku = new BufferedReader(new FileReader(dosyaAdi));
            satir = oku.readLine();
            while (satir != null) {
                System.out.println(satir);
                satir = oku.readLine();
            }
            oku.close();
        } catch (IOException iox) {
            System.out.println(dosyaAdi + " adli dosya okunamiyor.");
        }
    }
    public static void secilenHemsire(String musaitOlmayanHemsireler) throws IOException {
        File file = new File("musaitOlmayanHemsireler.txt");

        FileWriter fileWriter = new FileWriter(file, true);
        //true çünkü dosya sonuna ekleme yapacak
        BufferedWriter bWriter = new BufferedWriter(fileWriter);

        bWriter.write(musaitOlmayanHemsireler);
        bWriter.newLine();

        fileWriter.close();
        bWriter.close();
    }
    //bu kısımda hemşireleri sadece 1 hasta tarafından alınabilir yapacağız
    //hasta işlemlerinde seçilen hemşire musaitOlmayan hemşireler dosyasında varsa alamayacak

    public static void hemsireSecme(){
        String selectedNurse = selectNurse("Nurses.txt");

        if (selectedNurse != null) {
            System.out.println("Seçilen hemşire: " + selectedNurse);
        } else {
            System.out.println("Geçersiz hemşire seçimi.");
        }
    }

    private static String selectNurse(String fileName) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Dosyadan hemşire isimlerini okuma
            List<String> nurseNames = readNurseNamesFromFile(fileName);

            // Kullanıcıdan bir hemşire seçmesi istenir
            System.out.print("Lütfen bir hemşire seçin: ");
             selectedNurse = scanner.nextLine();

            // Hemşire seçme işlemi
            boolean isValidNurse = nurseNames.contains(selectedNurse);

            // Eğer seçilen hemşire geçerli değilse tekrar seçim yapma isteği
            while (!isValidNurse) {
                System.out.print("Geçersiz hemşire. Lütfen tekrar seçin: ");
                selectedNurse = scanner.nextLine();
                isValidNurse = nurseNames.contains(selectedNurse);
            }

            return selectedNurse;
        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    private static List<String> readNurseNamesFromFile(String fileName) {
        List<String> nurseNames = new ArrayList<>();

        try {
            Path filePath = Paths.get(fileName);
            nurseNames = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }

        return nurseNames;
    }
}