# Server Monitoring & Analytics

## Overview
This project collects real-time server data from a Tomcat-based application using a JAR file initialized in `web.xml`. The collected data is transmitted to Firebase every second for analysis, providing insights into server performance, security, API usage, and user behavior.

## Collected Metrics
The system captures the following metrics:

- **Request Details**
  - `timestamp`: Time of request.
  - `method`: HTTP method (GET, POST, etc.).
  - `uri`: Requested endpoint.
  - `requestSize`: Size of incoming request.
  - `responseSize`: Size of response data.
  - `protocol`: Protocol used (HTTP/1.1, HTTP/2, etc.).
  - `contentType`: Response content type.

- **Server Details**
  - `schema`: Schema of the request.
  - `serverName`: Name of the server handling the request.
  - `localAddress`: Server's local IP address.
  - `localName`: Local hostname of the server.

- **Client & User Information**
  - `headers`: Request headers.
  - `locale`: User's locale.
  - `userAgent`: User’s browser and device details.

- **Query & Parameters**
  - `parameters`: Request parameters.
  - `queryString`: Query string of the request.

- **Memory Usage**
  - `totalMemory`: Total memory allocated to the server.
  - `freeMemory`: Available memory.
  - `usedMemory`: Currently used memory.

## Insights & Analysis
This collected data enables various types of analysis:

### 1. **Server Performance & Load Monitoring**
- Track **request rates** and identify peak traffic times.
- Monitor **memory usage trends** to detect potential memory leaks.
- Measure **API throughput** and detect slow response times.

### 2. **Security & Threat Detection**
- Detect **malicious activities** such as SQL injection and XSS via `parameters` and `queryString`.
- Identify **brute force attacks** by tracking repeated login attempts.
- Recognize **DDoS attacks** by analyzing sudden traffic spikes.

### 3. **API & Application Optimization**
- Identify **slow-performing endpoints** and optimize them.
- Detect **large response sizes** that may impact performance.
- Analyze **protocol usage trends** and enforce HTTPS where needed.

### 4. **User & Device Analytics**
- Track **most commonly used browsers and devices**.
- Identify **geographic distribution** of users using the `locale` metric.

### 5. **Predictive Monitoring & Alerts**
- Use machine learning models to **predict server failures**.
- Identify **long-term trends in resource consumption**.

## Setup & Integration
1. **Deploy the JAR**
  - Place the monitoring JAR inside your Tomcat server’s `lib` directory.
  - Initialize it in `web.xml` to start capturing data.

2. **Configure Firebase**
  - Set up Firebase Realtime Database.
  - Update the JAR configuration with Firebase credentials.

3. **Start Collecting & Analyzing Data**
  - Logs are sent every second to Firebase.
  - Use Firebase queries or dashboards for real-time monitoring.


Example of data sent to Firebase:
```json
{
    "timestamp":"[long]",
    "method":"[String]",
    "uri":"[String]",
    "requestSize":"[long]",
    "responseSize":"[long]",
    "protocol":"[String]",
    "contentType":"[String]",
    "schema":"[String]",
    "serverName":"[String]",
    "ipV4Address":"[String]",
    "ipV6Address":"[String]",
    "localName":"[String]",
    "contentType":"[String]",
    "locale":"[String]",
    "userAgent":"[String]",
    "parameters":"[String]",
    "queryString":"[String]",
    "totalMemory":"[long]",
    "freeMemory":"[long]",
    "usedMemory":"[long]"
}
