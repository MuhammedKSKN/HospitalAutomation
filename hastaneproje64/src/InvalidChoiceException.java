//Adminde şifre yanlış girince tekrar denemek için q çıkmak için exit var orada yanlış seçenek tuşlanınca bu ahtayı veriyor
public class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message);
    }
}