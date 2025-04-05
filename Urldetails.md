# Server ↔ DB 

## Overview

This document outlines the format, schedule, and content of data exchanged between the client/server and the database. The data covers system hardware, OS, performance metrics, and installed components. Some data is transmitted once, while others are collected and sent at regular intervals (scheduled/time-series data).

---

## TimeSeriesURL Format

Used for time-based metrics reporting:

```
/[year]/[month]/[date]/[hrs]/[minutes]
```

---

## URL Structure

```
[ServerUrl]/[USERKEY]/[UUID]/<component>/<subcomponent>.json
```

For time-series data:

```
[ServerUrl]/[USERKEY]/[UUID]/<component>/<subcomponent>/<TimeSeriesURL>.json
```

---

## Endpoint Communication

### Endpoint Discovery

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/endpoint.json
  ```
- **Payload**: `~[JSONArray]`

Purpose:
- Send the list of all available endpoint URLs to the DB 
- or
- Retrieve the list of available endpoints from the DB

---

## Hardware Information

### CPU

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/CPU.json
  ```
- **Payload**:

```json
{
  "ModelName": "...",
  "Architecture": "...",
  "PhysicalCores": ...,
  "LogicalCores": ...,
  "clockSpeed": "...",
  "Vendor": "...",
  "CacheSize": "..."
}
```

---

### Memory

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory.json
  ```
- **Payload**:

```json
{
  "TotalInstallMemory": "...",
  "MemorySpeed": "...",
  "MemoryType": "...",
  "MemoryChannelCount": ...,
  "InstalledModuleCount": ...
}
```

#### Memory Statistics (Scheduled)

- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Memory/MemoryStats/[TimeSeriesURL].json
  ```
- **Payload**:

```json
[
  {
    "FreeMemory": ...,
    "UsedMemory": ...
  }
]
```

---

### Storage

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Storage.json
  ```
- **Payload**:

```json
{
  "DiskPresent": true,
  "DiskModel": "...",
  "DiskVendor": "...",
  "DiskSize": "...",
  "interfaceType": "...",
  "PartitionLayout": "...",
  "readSpeed": "...",
  "WriteSpeed": "...",
  "SmartStatus": "..."
}
```

---

### GPU

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/GPU.json
  ```
- **Payload**:

```json
{
  "GPUModel": "...",
  "GPUVendor": "...",
  "DriverSize": "..."
}
```

---

### Network

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Network.json
  ```
- **Payload**:

```json
{
  "NICModel": "...",
  "MacAddress": "...",
  "InterfaceSpeed": "...",
  "IpAddress": "...",
  "InterfaceStatus": "..."
}
```

---

### Motherboard

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/Motherboard.json
  ```
- **Payload**:

```json
{
  "manufacturer": "...",
  "Model": "...",
  "SerialNumber": "...",
  "BIOS/UEFIVersion": "...",
  "BaseBoardVersion": "..."
}
```

---

### Operating System

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/hardware/OS.json
  ```
- **Payload**:

```json
{
  "OSName": "...",
  "OSVersion": "...",
  "OSArch": "...",
  "UserName": "...",
  "UserHost": "...",
  "UserDir": "..."
}
```

---

## Performance Metrics

### URL Method Count (Scheduled)

- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/performance/UrlMethodCount/[TimeSeriesURL].json
  ```
- **Payload**:

```json
[
  {
    "GET": ...,
    "PUT": ...,
    "POST": ...,
    "DELETE": ...
  }
]
```

---

### URL Response Metrics (Scheduled)

- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/performance/UrlResponseCount/[TimeSeriesURL].json
  ```
- **Payload**:

```json
[
  {
    "AverageResponseSize": ...,
    "PerformanceSpeed": ...,
    "RequestRate": ...,
    "ResponseTime": ...
  }
]
```

---

## Installed Components

### JAR Files

- **Frequency**: Once  
- **URL**:  
  ```
  [ServerUrl]/[USERKEY]/[UUID]/Components/jars.json
  ```
- **Payload**:  
  `~[JSONArray]` – A list of installed JAR files.

---

