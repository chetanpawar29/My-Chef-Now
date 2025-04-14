# 🍽️ My-Chef-Now — Online Chef Booking Platform

Welcome to **My-Chef-Now**, a powerful Spring Boot backend project that allows users to **book personal chefs online**, while enabling chefs to manage bookings and admins to manage chefs and users. This RESTful application incorporates modern **Spring Boot MVC architecture**, **Spring Security with JWT-based authentication**, **AOP**, and **Spring Data JPA**.

🔗 **GitHub Repository**: [My-Chef-Now](https://github.com/chetanpawar29/My-Chef-Now)

---

## 🌟 Tech Stack

- 💡 Spring Boot (MVC)
- 🔐 Spring Security + JWT (Role-based authentication and authorization)
- 🔄 REST API with Postman Testing
- 🧩 AOP (Aspect Oriented Programming)
- 🛢️ Spring Data JPA (Database Access Layer)
- 🐘 PostgreSQL

---

## 🧑‍🍳 Project Roles & Flow

### 👑 Platform Admin:
- Login
- View chefs by status (Pending, Approved, Disapproved)
- Approve/Disapprove chefs
- View all customers

### 👨‍🍳 Chef:
- Register / Login
- Add Portfolio
- View/Manage bookings (Pending, Approved, Disapproved, Today's, By date)
- Change Password

### 👤 Customer:
- Register / Login
- View All Chefs (city-wise)
- Search Chef by Speciality (city-wise)
- Book Chef
- View Bookings (with status)
- Change Password

---

## 🔐 Authentication

- JWT-based login for all roles (Admin, Chef, Customer)
- Role-based authorization and protected endpoints

---

## 📬 API Endpoints

### 📁 Admin Controller (`/admin`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin/home` | Welcome message for Admin |
| GET | `/admin/chefs/{chefStatus}` | View chefs by status (`Pending`, `Approved`, `Disapproved`) |
| PUT | `/admin/chefStatus/{chefId}/{chefStatus}` | Approved/Disapproved chef |
| GET | `/admin/customers` | View all registered customers |

---

### 📁 Chef Controller (`/chef`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/chef/home` | Welcome message for Chef |
| GET | `/chef/booking/pending/{chefId}` | View pending bookings |
| PUT | `/chef/bookingStatus/{bookingId}/{bookingStatus}` | Approved/Disapproved booking |
| GET | `/chef/booking/today's/{chefId}` | View today’s bookings |
| GET | `/chef/booking/date-wise/{chefId}` | View bookings by date |
| GET | `/chef/booking/status/approve/{chefId}` | View approved bookings |
| GET | `/chef/booking/status/disapprove/{chefId}` | View disapproved bookings |
| PUT | `/chef/password/{chefId}` | Change password (requires email & new password) |

---

### 📁 Customer Controller (`/customer`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/customer/home` | Welcome message for Customer |
| PUT | `/customer/password/{customerId}` | Change password (requires email & new password) |
| GET | `/customer/chefs/city-wise` | View all chefs (grouped by city) |
| GET | `/customer/chefs/search/speciality/{speciality}` | Search chef by speciality |
| POST | `/customer/book/customerId={customerId}/chefId={chefId}` | Book a chef |
| GET | `/customer/myBooking/{customerId}` | View all bookings of logged-in customer |

---

## 🔧 Setup & Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/chetanpawar29/My-Chef-Now.git
2. Import as Maven project in your IDE (e.g., IntelliJ / Eclipse).
3. Update application.properties with your MySQL config:
   ```bash
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
4. Run the application:
   ```bash
   mvn spring-boot:run
5. Test APIs using Postman (Recommended).
---
## 📬 Postman Collection
You can test all the endpoints using Postman. Make sure to:

- Login and get the JWT Token

- Use the token in the Authorization header as:
  ```bash
   Bearer <your_token>
---
## 🛡️ Security Features
You can test all the endpoints using Postman. Make sure to:

- Tokens are time-bound and expire after a configurable duration to enhance session safety.

- Passwords are securely stored in the database using **hashing algorithms (e.g., BCrypt)**.

- This token is used to authenticate and authorize access to secured endpoints.
---
## 🤝 Contributing
Contributions are welcome!
If you find any bugs or want to enhance features, feel free to submit a pull request.
  
