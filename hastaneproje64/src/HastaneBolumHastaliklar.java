import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HastaneBolumHastaliklar  {

    public static void HastalikYazdir ()throws IOException {
        // Dosya yolu
        String dosyaYolu = "hastanebolumhastaliklar.txt";

        // Boş bir HashMap oluştur, her bölüm için bir liste içerecek
        HashMap<String, List<String>> bolumHastaliklar = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("hastanebolumhastaliklar.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Satırdaki bilgileri işleyerek HashMap'i doldur
                String[] parts = line.split(";");
                String bolum = parts[0];
                List<String> hastaliklar = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    hastaliklar.add(parts[i]);
                }

                // Bölüm zaten HashMap'te varsa, hastalıkları listeye ekle
                if (bolumHastaliklar.containsKey(bolum)) {
                    bolumHastaliklar.get(bolum).addAll(hastaliklar);
                }
                // Bölüm HashMap'te yoksa, yeni bir liste oluştur
                else {
                    bolumHastaliklar.put(bolum, hastaliklar);
                }
            }

            // Her bölüm için oluşturulan listeleri ekrana yazdır
            for (String bolum : bolumHastaliklar.keySet()) {
                List<String> hastaliklar = bolumHastaliklar.get(bolum);
                System.out.println(bolum + ": " + String.join("; ", hastaliklar));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//burada ekrana tazdırmayacak ; ile dosyada gezecek hastalık adına göre rastgele bir seçim yapacak
//direk ARRAYlist oluşturalım daha mantıklı