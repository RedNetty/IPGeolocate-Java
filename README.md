# IPGeolocate-Java

A Java library and desktop application for resolving IP addresses to geographic locations using the [ipgeolocation.io](https://ipgeolocation.io/) API. Provides city, region, country, timezone, ISP, and coordinates for any public IP — or auto-detects your own.

## Features

- Look up geolocation data for any public IPv4 or IPv6 address
- Auto-detect and resolve the caller's own IP
- Returns city, region/state, country, coordinates, timezone, ISP, and more
- Clean Swing desktop UI
- API key loaded from environment variable — no credentials in source

## Prerequisites

- Java 8+
- Maven 3.6+
- A free API key from [ipgeolocation.io](https://ipgeolocation.io/)

## Configuration

Set your API key as an environment variable before running:

```bash
# Linux / macOS
export IPGEO_API_KEY=your_api_key_here

# Windows
set IPGEO_API_KEY=your_api_key_here
```

> The application reads `IPGEO_API_KEY` at startup. Never hardcode credentials in source files.

## Build & Run

```bash
git clone https://github.com/RedNetty/IPGeolocate-Java.git
cd IPGeolocate-Java
mvn clean package
java -jar target/geolocation-app.jar
```

## Usage

1. Launch the application
2. Enter an IP address in the text field, or leave it empty to look up your own IP
3. Click **Fetch Location**
4. Results appear in the panel below — city, region, country, coordinates, ISP, timezone

## API Reference

Core class: `IPGeolocator`

```java
// Look up a specific IP
IPGeolocator geo = new IPGeolocator(System.getenv("IPGEO_API_KEY"));
GeoLocation result = geo.lookup("8.8.8.8");

System.out.println(result.getCity());      // Mountain View
System.out.println(result.getCountry());   // United States
System.out.println(result.getLatitude());  // 37.386
System.out.println(result.getLongitude()); // -122.083
```

## Project Structure

```
src/main/java/
└── com/rednetty/ipgeolocate/
    ├── IPGeolocator.java     # API client and response parsing
    ├── GeoLocation.java      # Location data model
    ├── GeoLocatorUI.java     # Swing desktop UI
    └── Main.java             # Entry point
```

## License

MIT License.
