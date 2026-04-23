import java.sql.*;
import java.util.*;

class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    HashMap<Integer, Integer> issuedBooks = new HashMap<>(); 
    // bookId -> userId

    HashMap<Integer, Integer> issueDays = new HashMap<>(); 
    // bookId -> day number (for fine)

    // ---------------- ADD BOOK ----------------
   void addBook(int id, String title, String author) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "INSERT INTO books VALUES (?, ?, ?, false)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, author);

        ps.executeUpdate();

        System.out.println("Book added to database!");

    } catch (Exception e) {
        System.out.println(e);
    }
}

    // ---------------- VIEW BOOKS ----------------
   void viewBooks() {
    try {
        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM books";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(
                rs.getInt("book_id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") + " | Issued: " +
                rs.getBoolean("is_issued")
            );
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}

    // ---------------- REGISTER USER ----------------
    void addUser(int id, String name) {
        users.add(new User(id, name));
        System.out.println("User registered!");
    }

    // ---------------- ISSUE BOOK ----------------
    void issueBook(int bookId, int userId, int day) {
    try {
        Connection con = DBConnection.getConnection();

        // Step 1: Check book in DB
        String check = "SELECT * FROM books WHERE book_id = ?";
        PreparedStatement ps1 = con.prepareStatement(check);
        ps1.setInt(1, bookId);

        ResultSet rs = ps1.executeQuery();

        if (!rs.next()) {
            System.out.println("Book not found!");
            return;
        }

        boolean issued = rs.getBoolean("is_issued");

        if (issued) {
            System.out.println("Book already issued!");
            return;
        }

        // Step 2: Update book to issued
        String update = "UPDATE books SET is_issued = true WHERE book_id = ?";
        PreparedStatement ps2 = con.prepareStatement(update);
        ps2.setInt(1, bookId);

        ps2.executeUpdate();

        System.out.println("Book issued successfully!");

    } catch (Exception e) {
        System.out.println(e);
    }
}

    // ---------------- RETURN BOOK ----------------
   void returnBook(int bookId, int returnDay) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "SELECT is_issued, issue_day FROM books WHERE book_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, bookId);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("Book not found!");
            return;
        }

        int issued = rs.getInt("is_issued");

        if (issued == 0) {
            System.out.println("This book was not issued!");
            return;
        }

        int issueDay = rs.getInt("issue_day");
        int daysUsed = returnDay - issueDay;

        int fine = 0;
        if (daysUsed > 7) {
            fine = (daysUsed - 7) * 10;
        }

        // update book
        String update = "UPDATE books SET is_issued = 0, issue_day = NULL WHERE book_id = ?";
        PreparedStatement ps2 = con.prepareStatement(update);
        ps2.setInt(1, bookId);
        ps2.executeUpdate();

        System.out.println("Book returned!");
        System.out.println("Days used: " + daysUsed);
        System.out.println("Fine: Rs." + fine);

    } catch (Exception e) {
        System.out.println(e);
    }
}

    // ---------------- SEARCH BOOK ----------------
    void searchBook(String keyword) {
    try {
        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, "%" + keyword.toLowerCase() + "%");
        ps.setString(2, "%" + keyword.toLowerCase() + "%");

        ResultSet rs = ps.executeQuery();

        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.println(
                rs.getInt("book_id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author")
            );
        }

        if (!found) {
            System.out.println("No matching books found.");
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}
}