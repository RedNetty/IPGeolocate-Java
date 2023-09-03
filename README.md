# IPGeolocate-Java

## Description

The Geolocation App is a Java-based desktop application that allows users to fetch location information based on IP addresses. It utilizes the IPGeolocationAPI to provide accurate geolocation details including city, state, country, and more.

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [Contributing](#contributing)
4. [License](#license)
5. [Acknowledgements](#acknowledgements)

## Installation

### Prerequisites

- Java JDK 8 or higher
- Maven
- https://ipgeolocation.io/ API KEY

### Steps

1. Clone the repository to your local machine.
   git clone https://github.com/yourusername/Geolocation-App.git

2. Navigate into the project folder.
   cd Geolocation-App

3. Input your API Key from https://ipgeolocation.io/ in the Main Class

4. Build the project.
   mvn clean install

5. Run the application.
   java -jar target/geolocation-app.jar

## Usage

1. Open the application.
2. Input an IP address into the text field, or leave it blank to fetch your own location.
3. Click the 'Fetch My Location' or 'Fetch IP Location' button.
4. View the fetched data in the text area below.

## Contributing

If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b new-feature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin new-feature`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

- Thanks to IPGeolocationAPI for providing the geolocation services.
- [Font Awesome](https://fontawesome.com/) for the icons.

