# Contact Management System

A full-stack application for managing contacts with CRUD operations and duplicate contact management functionality. Built with Spring Boot and React.

![Project Banner](https://i.ibb.co/WW1dYF3/Dashboard.png)

## Features

- ✅ Create, Read, Update, and Delete contacts
- 🔄 Automatic duplicate contact detection and merging
- ✉️ Email and mobile number uniqueness validation
- 📱 Responsive design using Bootstrap
- ⚡ Real-time form validation & secured with JWT Auth
- 🎯 Field-specific validation rules
  - First/Last name: Only alphabets
  - Email: Valid email format
  - Mobile: 10-digit format
  - Address: Optional field

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Spring Validation

### Frontend
- React 18
- Bootstrap 5
- Axios
- React-Bootstrap
- React-Icons

## Prerequisites

Before running this application, make sure you have the following installed:
- Java 17 or higher
- Node.js 14 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

## Installation & Setup

### Backend Setup

1. Clone the repository
```bash
git clone https://github.com/yourusername/contact-management.git
cd contact-management
```

2. Configure PostgreSQL
```sql
CREATE DATABASE contact_management;
```

3. Update database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/contact_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Build and run the Spring Boot application
```bash
mvn clean install
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory
```bash
cd contact-management-frontend
```

2. Install dependencies
```bash
npm install
```

3. Start the development server
```bash
npm start
```

The frontend application will start on `http://localhost:3000`

## API Endpoints

ProstMan Collection : [https://www.postman.com/cryosat-candidate-56059520/myworkspace/collection/yd4mywg/contact-management-application](https://www.postman.com/cryosat-candidate-56059520/myworkspace/collection/yd4mywg/contact-management-application)

### Contacts API
| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/contacts/getAllContacts` | Get all contacts |
| GET | `/api/contacts/getContact{id}` | Get contact by ID |
| POST | `/api/contacts/createContact` | Create new contact |
| PUT | `/api/contacts/updateContact/{id}` | Update existing contact |
| DELETE | `/api/contacts/deleteContact/{id}` | Delete contact |
| POST | `/api/contacts/mergeContacts/{mergeBy}` | Merge duplicate contacts |

### Request/Response Examples

#### Create Contact Request
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com",
  "mobile": "1234567890",
  "address": "123 Main St, City"
}
```

#### Contact Response
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com",
  "mobile": "1234567890",
  "address": "123 Main St, City"
}
```

## Project Structure

### Backend Structure
```
contact-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── contactmanagement/
│   │   │               ├── ContactManagementApplication.java
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               ├── exception/
│   │   │               └── dto/
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

### Frontend Structure
```
contact-management-frontend/
├── public/
├── src/
│   ├── components/
│   │   ├── ContactList.js
│   │   ├── ContactForm.js
│   │   └── ContactCard.js
│   ├── services/
│   │   └── contactService.js
│   ├── App.js
│   └── index.js
└── package.json
```

## Development

### Running Tests
```bash
# Backend tests
mvn test

# Frontend tests
cd contact-management-frontend
npm test
```

### Building for Production
```bash
# Backend
mvn clean package

# Frontend
cd contact-management-frontend
npm run build
```

## Common Issues & Solutions

1. **CORS Issues**
   - Ensure the CORS configuration in the backend matches your frontend URL
   - Check if the backend server is running on the correct port

2. **Database Connection**
   - Verify PostgreSQL is running
   - Check database credentials in application.properties
   - Ensure database schema matches the entity definitions

3. **Frontend API Calls**
   - Verify the API_URL in contactService.js matches your backend URL
   - Check browser console for any CORS or network errors

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details

## Acknowledgments

- Spring Boot Documentation
- React Documentation
- Bootstrap Documentation
- PostgreSQL Documentation

## Contact

Your Name - [@yourusername](https://twitter.com/yourusername)

Project Link: [https://github.com/yourusername/contact-management](https://github.com/yourusername/contact-management)
