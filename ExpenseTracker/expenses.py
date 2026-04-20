import csv
import matplotlib.pyplot as plt

FILE_NAME = 'expenses.csv'


# -------------------- ADD EXPENSE --------------------
def add_expense():
    date = input("Enter date (DD-MM-YYYY): ").strip()
    category = input("Enter category: ").strip()
    amount = input("Enter amount: ").strip()
    description = input("Enter description: ").strip()

    with open(FILE_NAME, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount, description])

    print("Expense added successfully!")


# -------------------- VIEW EXPENSES --------------------
def view_expenses():
    try:
        with open(FILE_NAME, 'r', encoding='utf-8-sig') as file:
            reader = csv.reader(file)
            next(reader)

            print("\nDate        Category      Amount    Description")
            print("------------------------------------------------")

            for row in reader:
                print(f"{row[0]:<12}{row[1]:<14}{row[2]:<10}{row[3]}")
    except FileNotFoundError:
        print("No data file found.")


# -------------------- MONTHLY SUMMARY --------------------
def monthly_summary():
    month = input("Enter month (MM-YYYY): ").strip()
    total = 0

    with open(FILE_NAME, 'r', encoding='utf-8-sig') as file:
        reader = csv.reader(file)
        next(reader)

        for row in reader:
            date = row[0].strip()
            amount = int(row[2])

            if date[3:10] == month:
                total += amount

    print(f"Total expense for {month}: ₹ {total}")


# -------------------- CATEGORY ANALYSIS --------------------
def category_analysis():
    category_totals = {}

    with open(FILE_NAME, 'r', encoding='utf-8-sig') as file:
        reader = csv.reader(file)
        next(reader)

        for row in reader:
            category = row[1].strip()
            amount = int(row[2])

            if category in category_totals:
                category_totals[category] += amount
            else:
                category_totals[category] = amount

    print("\nCategory-wise expenses:")
    for cat, amt in category_totals.items():
        print(f"{cat}: ₹ {amt}")

    if category_totals:
        highest = max(category_totals, key=category_totals.get)
        print(f"\nHighest spending category: {highest} (₹ {category_totals[highest]})")

        if category_totals:
            highest = max(category_totals, key=category_totals.get)
            print(f"\nHighest spending category: {highest} (₹ {category_totals[highest]})")

            print("\nSuggestion:")
            print(f"Try reducing spending on {highest}, as it is your highest expense category.")
    return category_totals

# -------------------- PIE CHART --------------------
def show_pie_chart():
    data = category_analysis()

    if not data:
        print("No data to display.")
        return

    labels = list(data.keys())
    values = list(data.values())

    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Expense Distribution")
    plt.show()


# -------------------- MAIN MENU --------------------
def main():
    while True:
        print("\n====== Expense Tracker ======")
        print("1. Add Expense")
        print("2. View Expenses")
        print("3. Monthly Summary")
        print("4. Category Analysis")
        print("5. Show Pie Chart")
        print("6. Exit")

        choice = input("Enter choice: ").strip()

        if choice == '1':
            add_expense()
        elif choice == '2':
            view_expenses()
        elif choice == '3':
            monthly_summary()
        elif choice == '4':
            category_analysis()
        elif choice == '5':
            show_pie_chart()
        elif choice == '6':
            print("Exiting...")
            break
        else:
            print("Invalid choice. Try again.")


# Run program
main()
