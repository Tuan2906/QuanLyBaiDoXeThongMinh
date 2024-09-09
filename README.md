

# Smart Parking Lot Management System

This project involves building a high-performance backend server for managing parking lots using Spring MVC and Spring Security.

## Features

- **Retrieve Parking Lot Information:** Fetch information about parking lots with filtering and search options (GET).
- **Parking Spot Registration Information:** Manage parking spot registration with options for retrieving (GET) and creating (POST) registrations.
- **Cancel Parking Reservations:** Allow users to cancel parking reservations.
- **Registered Vehicle Information:** Manage vehicle information with options for retrieving (GET), creating (POST), and updating (PATCH) records.
- **Email Notifications:** Send email notifications.
- **Scheduled Email Notifications:** Schedule email notifications for various events.

## Technologies Used

- **Backend Framework:** Spring MVC
- **Security Framework:** Spring Security
- **Email Handling:** JavaMail API

## Installation

1. **Clone the Project:**
   ```bash
   git clone https://github.com/Tuan2906/SmartParkingLotManagement.git
   cd QuanLyBaiDoXe
   ```

2. **Set Up the Environment:**
   - Ensure you have JDK 15.
   - Configure your database and update the `application.properties` file with the correct database connection details.

3. **Build and Run the Application:**
   ```bash
   ./mvnw clean install
   cp target/QuanLyBaiDoXe.war /opt/tomcat/webapps/
   cd /opt/tomcat/bin
   ./catalina.sh start

   ```


## Usage Guide

1. **Retrieve Parking Lot Information:** Use the GET `/parking-lots` endpoint to get information about parking lots. Apply filters and search parameters as needed.
2. **Manage Reservations:** Use GET and POST `/reservations` to handle parking spot registrations, and DELETE `/reservations/{id}` to cancel them.
3. **Manage Vehicles:** Use GET, POST, and PATCH endpoints for managing vehicle information.
4. **Email Notifications:** Send or schedule email notifications using the respective endpoints.

## Contributing

If you would like to contribute to the project, please fork the repository and submit a pull request. Contributions are welcome!

## Contact

For questions or feedback, please reach out via email: tuanchaunguyen13@gmail.com.

## License

This project is licensed under the [MIT License](LICENSE).
