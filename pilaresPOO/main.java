package pilaresPOO;

public class main {
    public static void main(String[] args) {
        // Primeiro livro
        books book1 = new books("1984", "George Orwell", "1234567890", 1949);
        book1.displayInfo();
        book1.addReview("Awesome book!");

        System.out.println("-----------------------");

        // Segundo livro
        books book2 = new books("To Kill a Mockingbird", "Harper Lee", "0987654321", 1960);
        book2.displayInfo();
        book2.addReview("A timeless classic.");

        System.out.println("-----------------------");

        // Terceiro livro
        books book3 = new books("The Great Gatsby", "F. Scott Fitzgerald", "1122334455", 1925);
        book3.displayInfo();
        book3.addReview("A fascinating glimpse into the Jazz Age.");
    }
}
