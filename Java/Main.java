import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Library lib = new Library();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register User");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Book");
            System.out.println("7. Exit");
            
            System.out.print("Enter choice: ");
            if (!sc.hasNextInt()) {
            System.out.println("Invalid input!");
            sc.next(); 
            continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt(); sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    lib.addBook(id, title, author);
                    break;

                case 2:
                    lib.viewBooks();
                    break;

                case 3:
                    System.out.print("User ID: ");
                    int uid = sc.nextInt(); sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    lib.addUser(uid, name);
                    break;

                case 4:
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();

                    System.out.print("User ID: ");
                    int u = sc.nextInt();

                    System.out.print("Issue Day (number): ");
                    int day = sc.nextInt();

                    lib.issueBook(bid, u, day);
                    break;

                case 5:
                    System.out.print("Book ID: ");
                    int rb = sc.nextInt();

                    System.out.print("Return Day: ");
                    int rd = sc.nextInt();

                    lib.returnBook(rb, rd);
                    break;

                case 6:
                    System.out.print("Search keyword: ");
                    String key = sc.nextLine();

                    lib.searchBook(key);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}