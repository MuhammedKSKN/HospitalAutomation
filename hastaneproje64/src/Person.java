    public abstract class Person {
        // Alanlar
        private String name;
        private int age;


        // Kurucu
        public Person(String name, int age) {
            this.name = name;
            this.age = age;

        }

        // Yöntemler
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }}



