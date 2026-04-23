# 📚 Library Management System (Java + MySQL)

## 📌 Project Overview

This project is a console-based Library Management System developed using Core Java and MySQL. It automates basic library operations such as managing books, registering users, issuing and returning books, and calculating fines.

---

## 🎯 Objectives

* Manage books and users efficiently
* Track issued and returned books
* Automate fine calculation for late returns
* Store data permanently using MySQL

---

## 🛠️ Technologies Used

* **Java (Core Java, OOP concepts)**
* **MySQL (Database)**
* **JDBC (Java Database Connectivity)**
* **VS Code (Development Environment)**

---

## 🗄️ Database Schema

### Books Table

* `book_id` (Primary Key)
* `title`
* `author`
* `is_issued` (0 = available, 1 = issued)
* `issue_day` (for fine calculation)

### Users Table

* `user_id` (Primary Key)
* `name`

---

## ⚙️ Features Implemented

### 📘 Book Management

* Add new books
* View all books

### 👤 User Management

* Register new users

### 🔄 Transactions

* Issue books
* Return books
* Track book availability

### 💰 Fine Calculation

* First 7 days → No fine
* After 7 days → ₹10 per extra day

### 🔍 Search

* Search books by title or author

---

## ▶️ How to Run

### 1. Compile

```bash
javac -cp ".;mysql-connector-j-9.7.0.jar" *.java
```

### 2. Run

```bash
java -cp ".;mysql-connector-j-9.7.0.jar" Main
```

---

## 🧪 Sample Output

```
===== Library Menu =====
1. Add Book
2. View Books
3. Register User
4. Issue Book
5. Return Book
6. Search Book
7. Exit
```

---

## 📊 Example Fine Calculation

```
Issue Day: 10
Return Day: 20

Days used = 10
Free days = 7
Extra days = 3
Fine = Rs.30
```

---

## 🧠 Key Concepts Used

* Object-Oriented Programming (Classes, Objects)
* JDBC for database connectivity
* SQL queries (INSERT, SELECT, UPDATE)
* Exception handling
* Menu-driven CLI system

---

## 🎯 Outcome

This system reduces manual effort in managing library records and ensures accurate tracking of books and users with persistent data storage.

---

## 🚀 Future Improvements

* GUI using Swing or JavaFX
* Real date handling using `LocalDate`
* Delete/update book feature
* Advanced reporting (issued books, available books)

---

## 👨‍💻 Author

Pavan Kalyan
