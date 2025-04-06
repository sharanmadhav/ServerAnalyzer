# Server-to-Database Communication

## Overview

This document describes how data is exchanged between the server and the database. It includes the format, schedule, and details of the data being shared, such as system hardware, operating system (OS), performance metrics, and installed components. Some data is sent only once, while other data is collected and transmitted on a regular schedule.

---

## Time-Series URL Format

For metrics tracked over time, the URL includes a timestamp in the following format:

```
/[year]/[month]/[date]/[hours]/[minutes]
```

Example: `/2025/04/06/14/30` (April 6, 2025, 2:30 PM)

---

## General URL Structure

- **Static Data**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/<component>/<subcomponent>.json
  ```
- **Time-Series Data**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/<component>/<subcomponent>/<TimeSeriesURL>.json
  ```

- `[ServerUrl]`: Base server address  openapi: 3.0.3
info:
  title: Server-to-Database API
  description: |
    This API defines the communication between a server and a database, including system hardware, operating system details, performance metrics, and installed components. Data is exchanged via static endpoints (sent once) and time-series endpoints (sent on a schedule).
  version: 1.0.0

servers:
  - url: "{serverUrl}"
    description: Base URL of the server
    variables:
      serverUrl:
        default: "https://example.com"
        description: The base server address

paths:
  /{userKey}/endpoint.json:
    get:
      summary: Endpoint Discovery
      description: Retrieves a list of available endpoint URLs from the database.
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
          description: Unique user identifier
      responses:
        "200":
          description: List of endpoint URLs
          content:
            application/json:
              schema:
                type: array
                items:
                  type: string
    post:
      summary: Register Endpoints
      description: Sends a list of available endpoint URLs to the database.
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
          description: Unique user identifier
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: array
              items:
                type: string
      responses:
        "201":
          description: Endpoints successfully registered

  /{userKey}/{uuid}/hardware/CPU.json:
    post:
      summary: Submit CPU Information
      description: Sends CPU hardware details to the database (one-time submission).
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
          description: Unique user identifier
        - name: uuid
          in: path
          required: true
          schema:
            type: string
          description: Unique system identifier
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/CPU"
      responses:
        "201":
          description: CPU data successfully submitted

  /{userKey}/{uuid}/hardware/Memory.json:
    post:
      summary: Submit Memory Information
      description: Sends memory hardware details to the database (one-time submission).
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
        - name: uuid
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Memory"
      responses:
        "201":
          description: Memory data successfully submitted

  /{userKey}/{uuid}/hardware/Memory/MemoryStats/{year}/{month}/{date}/{hours}/{minutes}.json:
    post:
      summary: Submit Memory Statistics
      description: Sends scheduled memory usage statistics to the database.
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
        - name: uuid
          in: path
          required: true
          schema:
            type: string
        - name: year
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{4}$"
        - name: month
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: date
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: hours
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: minutes
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: array
              items:
                $ref: "#/components/schemas/MemoryStats"
      responses:
        "201":
          description: Memory stats successfully submitted

  # Add other hardware endpoints (Storage, GPU, Network, Motherboard, OS) similarly...

  /{userKey}/{uuid}/performance/UrlMethodCount/{year}/{month}/{date}/{hours}/{minutes}.json:
    post:
      summary: Submit URL Method Count
      description: Sends scheduled counts of HTTP methods used by the server.
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
        - name: uuid
          in: path
          required: true
          schema:
            type: string
        - name: year
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{4}$"
        - name: month
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: date
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: hours
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
        - name: minutes
          in: path
          required: true
          schema:
            type: string
            pattern: "^[0-9]{2}$"
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: array
              items:
                $ref: "#/components/schemas/UrlMethodCount"
      responses:
        "201":
          description: URL method count successfully submitted

  /{userKey}/{uuid}/Components/jars.json:
    post:
      summary: Submit JAR Files List
      description: Sends a list of installed JAR files to the database (one-time submission).
      parameters:
        - name: userKey
          in: path
          required: true
          schema:
            type: string
        - name: uuid
          in: path
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: array
              items:
                type: string
      responses:
        "201":
          description: JAR files list successfully submitted

components:
  schemas:
    CPU:
      type: object
      properties:
        ModelName:
          type: string
        Architecture:
          type: string
        PhysicalCores:
          type: integer
        LogicalCores:
          type: integer
        ClockSpeed:
          type: string
        Vendor:
          type: string
        CacheSize:
          type: string
      required:
        - ModelName
        - Architecture
        - PhysicalCores
        - LogicalCores

    Memory:
      type: object
      properties:
        TotalInstalledMemory:
          type: string
        MemorySpeed:
          type: string
        MemoryType:
          type: string
        MemoryChannelCount:
          type: integer
        InstalledModuleCount:
          type: integer
      required:
        - TotalInstalledMemory

    MemoryStats:
      type: object
      properties:
        FreeMemory:
          type: string
        UsedMemory:
          type: string
      required:
        - FreeMemory
        - UsedMemory

    UrlMethodCount:
      type: object
      properties:
        GET:
          type: integer
        PUT:
          type: integer
        POST:
          type: integer
        DELETE:
          type: integer
      required:
        - GET
        - PUT
        - POST
        - DELETE

    # Add other schemas (Storage, GPU, Network, etc.) as needed...
- `[USERKEY]`: Unique user identifier  
- `[UUID]`: Unique system identifier  
- `<component>`: Category (e.g., hardware, performance)  
- `<subcomponent>`: Specific item (e.g., CPU, Memory)

---

## Endpoint Communication

### Endpoint Discovery

- **Purpose**: Share or retrieve a list of available endpoint URLs between the server and database.  
- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/endpoint.json
  ```
- **Payload**: A JSON array (`~[JSONArray]`) listing all endpoint URLs.

---

## Hardware Information

### CPU

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/CPU.json
  ```
- **Payload**:  
  ```json
  {
    "ModelName": "string",
    "Architecture": "string",
    "PhysicalCores": "number",
    "LogicalCores": "number",
    "ClockSpeed": "string",
    "Vendor": "string",
    "CacheSize": "string"
  }
  ```

---

### Memory

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory.json
  ```
- **Payload**:  
  ```json
  {
    "TotalInstalledMemory": "string",
    "MemorySpeed": "string",
    "MemoryType": "string",
    "MemoryChannelCount": "number",
    "InstalledModuleCount": "number"
  }
  ```

#### Memory Statistics (Scheduled)

- **Frequency**: Sent at regular intervals  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory/MemoryStats/[TimeSeriesURL].json
  ```
- **Payload**:  
  ```json
  [
    {
      "FreeMemory": "string",
      "UsedMemory": "string"
    }
  ]
  ```

---

### Storage

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Storage.json
  ```
- **Payload**:  
  ```json
  {
    "DiskPresent": "boolean",
    "DiskModel": "string",
    "DiskVendor": "string",
    "DiskSize": "string",
    "InterfaceType": "string",
    "PartitionLayout": "string",
    "ReadSpeed": "string",
    "WriteSpeed": "string",
    "SmartStatus": "string"
  }
  ```

---

### GPU

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/GPU.json
  ```
- **Payload**:  
  ```json
  {
    "GPUModel": "string",
    "GPUVendor": "string",
    "DriverSize": "string"
  }
  ```

---

### Network

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Network.json
  ```
- **Payload**:  
  ```json
  {
    "NICModel": "string",
    "MacAddress": "string",
    "InterfaceSpeed": "string",
    "IpAddress": "string",
    "InterfaceStatus": "string"
  }
  ```

---

### Motherboard

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Motherboard.json
  ```
- **Payload**:  
  ```json
  {
    "Manufacturer": "string",
    "Model": "string",
    "SerialNumber": "string",
    "BIOSorUEFIVersion": "string",
    "BaseboardVersion": "string"
  }
  ```

---

### Operating System

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/OS.json
  ```
- **Payload**:  
  ```json
  {
    "OSName": "string",
    "OSVersion": "string",
    "OSArchitecture": "string",
    "UserName": "string",
    "UserHost": "string",
    "UserDirectory": "string"
  }
  ```

---

## Performance Metrics

### URL Method Count (Scheduled)

- **Frequency**: Sent at regular intervals  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/performance/UrlMethodCount/[TimeSeriesURL].json
  ```
- **Payload**:  
  ```json
  [
    {
      "GET": "number",
      "PUT": "number",
      "POST": "number",
      "DELETE": "number"
    }
  ]
  ```

---

### URL Response Metrics (Scheduled)

- **Frequency**: Sent at regular intervals  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/performance/UrlResponseCount/[TimeSeriesURL].json
  ```
- **Payload**:  
  ```json
  [
    {
      "AverageResponseSize": "string",
      "PerformanceSpeed": "string",
      "RequestRate": "string",
      "ResponseTime": "string"
    }
  ]
  ```

---

## Build Update Logs

**Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/BuildUpdateLogs/[TimeSeriesURL].json
  ```
- **Payload**:  
  ```json
  {
    "TimeStamp": "string",
  }
  ```

---

## Alerts

**Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/Alerts/[TimeSeriesURL].json
  ```
- **Payload**:  
  ```json
  {
    "TimeStamp": "string",
    "Message":"string",
    "Level":"string"
  }
  ```

---

---

## Installed Components

### JAR Files

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/Components/jars.json
  ```
- **Payload**: A JSON array (`~[JSONArray]`) listing all installed JAR files.

---

