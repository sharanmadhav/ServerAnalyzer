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

- `[ServerUrl]`: Base server address  
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

## Installed Components

### JAR Files

- **Frequency**: Sent once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/Components/jars.json
  ```
- **Payload**: A JSON array (`~[JSONArray]`) listing all installed JAR files.

---

### Key Improvements:
1. **Clarity**: Simplified language and added explanations (e.g., what `[USERKEY]` means).
2. **Consistency**: Standardized terms (e.g., "Frequency" instead of mixing it with other phrasing) and JSON field names (e.g., "BIOS/UEFIVersion" → "BIOSorUEFIVersion").
3. **Professional Tone**: Removed informal phrasing and ensured a technical, polished style.
4. **Readability**: Added examples and broke up dense sections with bullet points and headers.

Let me know if you'd like further adjustments!