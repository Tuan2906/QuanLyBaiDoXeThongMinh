Dưới đây là phiên bản hoàn chỉnh của tài liệu README cho dự án của bạn:

---

# Smart Parking Lot Management System

This project involves building a high-performance backend server for managing parking lots using Spring MVC and Spring Security.

## Features

- **Retrieve Parking Lot Information:** Fetch information about parking lots with filtering and search options (GET).
- **Parking Spot Registration Information:** Manage parking spot registration with options for retrieving (GET) and creating (POST) registrations.
- **Cancel Parking Reservations:** Allow users to cancel parking reservations (DELETE).
- **Registered Vehicle Information:** Manage vehicle information with options for retrieving (GET), creating (POST), and updating (PATCH) records.
- **Email Notifications:** Send email notifications (POST).
- **Scheduled Email Notifications:** Schedule email notifications for various events (POST).

## Technologies Used

- **Backend Framework:** Spring MVC
- **Security Framework:** Spring Security
- **Email Handling:** JavaMail API

## Installation

1. **Clone the Project:**
   ```bash
   git clone https://github.com/Tuan2906/QuanLyBaiDoXe.git
   cd QuanLyBaiDoXe
   ```

2. **Set Up the Environment:**
   - Ensure you have JDK 15 installed.
   - Configure your database and update the `application.properties` file with the correct database connection details.

3. **Build and Deploy the Application:**
   ```bash
   ./mvnw clean install
   cp target/QuanLyBaiDoXe.war /opt/tomcat/webapps/
   cd /opt/tomcat/bin
   ./catalina.sh start
   ```

## Access the Application

After deploying the application, you can access it via your web browser. The URL for accessing the application typically has the following format:

```
http://localhost:8080/
```

- **`localhost`**: Your local machine (change this if you are deploying on a remote server).
- **`8080`**: The port on which Tomcat is listening (modify if you have configured Tomcat to use a different port).
- **`QuanLyBaiDoXe`**: The name of your WAR file (adjust the URL if your WAR file has a different name).

## Contributing

If you would like to contribute to the project, please fork the repository and submit a pull request. Contributions are welcome!

## Contact

For questions or feedback, please reach out via email: tuanchaunguyen13@gmail.com.

## License

This project is licensed under the [MIT License](LICENSE).

---
