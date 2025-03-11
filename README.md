# ServerAnalyzer

This is a Java-based utility that generates a JAR file to track incoming HTTP requests on web servers. It monitors details such as the request method, URI, payload sizes (request and response), and timestamps. The collected data is sent to Firebase as JSON.

## Features
- Tracks HTTP request details:
  - Timestamp
  - HTTP method (e.g., GET, POST)
  - URI of the request
  - Request payload size
  - Response payload size
- Sends data to Firebase in JSON format.
- Lightweight and easy to integrate into web servers.

Example of data sent to Firebase:
```json
[{"timestamp":"1741696972274","method":"GET","uri":"/_sv/connect.do","requestSize":-1,"responseSize":0}]
