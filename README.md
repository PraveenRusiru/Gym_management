Gym Management System

📌 Project Overview

The Gym Management System is a standalone software designed to help single-trainer gyms efficiently manage their clients, workout plans, nutrition tracking, payments, and progress monitoring. The system follows a layered architecture to ensure maintainability, scalability, and separation of concerns.

🏗️ Architecture Overview

This project follows a layered architecture, consisting of the following layers:
	1.	Presentation Layer (UI Layer)
	•	Built with JavaFX for a rich user experience.
	•	Uses Scene Builder for FXML-based UI design.
	•	Handles user interactions and sends requests to the service layer.
	2.	Service Layer (Business Logic Layer)
	•	Implements core application logic.
	•	Contains validation and transformation logic for data received from the UI layer.
	•	Manages interactions between the UI and data layers.
	3.	Data Access Layer (DAO Layer)
	•	Uses JDBC to interact with the MySQL database.
	•	Contains DAO (Data Access Object) classes for CRUD operations.
	•	Ensures data persistence and retrieval with optimized queries.
	4.	Database Layer (Persistence Layer)
	•	Stores all user, workout, nutrition, and payment data in a relational database.
	•	Uses MySQL for structured data storage.
	•	Enforces constraints like foreign keys, indexes, and normalization for efficiency.

🏋️ Key Features

✅ Client Management – Add, update, and track client details.
✅ Workout Program Management – Create and assign workout routines dynamically.
✅ Nutrition Tracking – Monitor daily calorie, protein, carb, and fat intake.
✅ Progress Monitoring – Track client strength levels, weight, and fat percentage over time.
✅ Gym Level Determination – Classify clients as beginner, intermediate, or advanced based on their progress.
✅ Payment System – Manage gym membership and schedule payments.
✅ Supplement Outlet – Optional feature to recommend and manage supplements.
✅ Report Generation – Generate reports on client progress, financials, and gym performance.

🛠️ Technologies Used
	•	Frontend: JavaFX (FXML, Scene Builder)
	•	Backend: Java (JDK 17+), JDBC
	•	Database: MySQL
	•	Build Tool: Maven
	•	Design Pattern: DAO Pattern
	•	Security: AES Encryption for password storage

📂 Project Structure

📦 GymManagementSystem  
 ┣ 📂 src  
 ┃ ┣ 📂 Controller          # Handles UI interactions  
 ┃ ┣ 📂 BO                  # Business logic layer  
 ┃ ┣ 📂 DAO                 # Data Access Layer   
 ┃ ┣ 📂 Entity              # Entity classes  
 ┃ ┣ 📂 util                # Utility functions (DB connection, validation, etc.)  
 ┃ ┗ 📂 DTO                 # Data travelling objects  
 ┃ ┗ 📂 DB                  # Database connections
 ┣ 📂 resources  
 ┃ ┣ 📂 cssForBody          # style sheets  
 ┃ ┣ 📂 material            # Images
 ┃ ┗ 📂 View                # fxml files
 ┣ 📜 pom.xml               # Maven dependencies  
 ┣ 📜 README.md             # Project documentation  
 ┗ 📜 GymManagementSystem.java  # Main application entry point  

🚀 Installation & Setup

Prerequisites
	•	Java JDK 17
	•	MySQL Server
	•	Maven

Steps to Run the Project
	1.	Clone the repository

git clone https://github.com/PraveenRusiru/Gym_management.git
cd GymManagementSystem


	2.	Configure Database
	•	Import the provided SQL file into MySQL.
	•	Update DB credentials in application.properties.
	3.	Build & Run

mvn clean install
mvn javafx:run

🔒 Security & Authentication
	•	User Authentication – Encrypted passwords using AES encryption.
	•	Role-Based Access – Separate access controls for admins and trainers.

📜 License

This project is licensed under the MIT License – feel free to modify and use it.

🤝 Contributing

We welcome contributions! Follow these steps to contribute:
	1.	Fork the repository.
	2.	Create a new branch (feature-branch).
	3.	Commit your changes.
	4.	Push the branch and open a Pull Request.

📧 Contact & Support

For any issues or feature requests, please open an issue or contact us at:
📩 praveenrusiru752@gmail.com

This README ensures clarity for new developers, making it easy to set up and understand the project. Let me know if you want any modifications! 🚀
