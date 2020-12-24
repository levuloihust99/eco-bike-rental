# INSTALLATION GUIDE
### Clone this project
```bash
$ git clone git@github.com:levuloihust99/TKXDPM.KSTN.20201-06.git
```

### Frontend (react-native)
#### Prerequisite
- npm (latest version)
- expo-cli 

#### Installation

```bash
$ cd EcoBikeRental
$ npm install

``` 

#### Run frontend
```bash
$ expo start
```

### Backend (spring boot + mysql)
#### Prerequisites
- java
- mysql server
- maven
- ngrok

#### Run mysql server
- Set port of mysql server 3306.

#### Create database and insert data
- Run SQL scripts to create database and insert data to table. The SQL scripts are in `DetailedDesgin/DataModeling/` directory.

#### Navigate to the project root directory
```bash
$ cd Program/Eco-backend
```

#### Build project
- Set the credentials in `src/main/resources/application.properties` file
```
server.port=<input-a-port>
spring.datasource.url=jdbc:mysql://localhost:3306/<your-schema>?useSSL=false
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

- Build project
```bash
$ mvn clean install
```

#### Run jar application
```bash
$ cd target/
$ java -jar be-1.0-SNAPSHOT.jar
```

#### Open http port
```bash
$ ngrok http <server.port>
```

Value the <server.port> is specified in `application.properties` file.

### Client device
- Use a iOS or Android phone.
- Go to AppStore or Google Play and download Expo app.
- Scan the QR code provided by frontend. For iOS > 11, scan the QR code by camera. For Android, open Expo app and you will see an option for scanning QR code.
