# Flight Data Analysis using Apache Hadoop, MapReduce and Pig

## University Project for Big Data Analytics

This project focuses on analyzing 2024 flight data using Apache Hadoop, HDFS, MapReduce, and Apache Pig. The main goal is to process flight records and extract useful information about airline operations, delays, cancellations, diversions, and delay causes.


## Problem Statement

Flight datasets contain a large amount of information about airline operations, departure delays, arrival delays, cancellations, diversions, and different causes of delays.

Analyzing this data manually can be difficult when the dataset becomes large. Therefore, Hadoop-based distributed processing techniques are used to process and analyze the flight dataset.

This project uses:

* HDFS for distributed storage
* MapReduce for data processing
* Apache Pig for data analysis


## Dataset

The project uses a **2024 Flight Data** CSV dataset.

Dataset Source: [Flight Delay Dataset — 2024](https://www.kaggle.com/datasets/hrishitpatil/flight-data-2024/data?select=flight_data_2024_sample.csv)

The dataset contains information about:

* Flight date
* Airline
* Flight number
* Origin
* Destination
* Departure time
* Departure delay
* Arrival time
* Arrival delay
* Cancellation
* Diversion
* Distance
* Carrier delay
* Weather delay
* NAS delay
* Security delay
* Late aircraft delay

### Important Fields

| Field                 | Description                      |
| --------------------- | -------------------------------- |
| `op_unique_carrier`   | Airline carrier code             |
| `origin`              | Origin airport                   |
| `dest`                | Destination airport              |
| `dep_delay`           | Departure delay                  |
| `arr_delay`           | Arrival delay                    |
| `cancelled`           | Whether the flight was cancelled |
| `diverted`            | Whether the flight was diverted  |
| `carrier_delay`       | Delay caused by carrier          |
| `weather_delay`       | Delay caused by weather          |
| `nas_delay`           | National Air System delay        |
| `security_delay`      | Security-related delay           |
| `late_aircraft_delay` | Delay caused by late aircraft    |


## Technologies Used

* Apache Hadoop 3.3.6
* Hadoop Distributed File System (HDFS)
* MapReduce
* Apache Pig 0.18.0
* Java
* Windows Command Prompt
* Git
* GitHub



# MapReduce Analysis

Three MapReduce programs were implemented in this project.

## 1. Total Flights by Airline

This analysis calculates the total number of flights operated by each airline.

### Output

```text
WN      2022
DL      1459
AA      1385
OO      1080
UA      1033
YX      434
MQ      406
NK      361
B6      331
OH      326
AS      323
F9      295
9E      278
G4      172
HA      95
```


## 2. Average Departure Delay

This MapReduce program calculates the average departure delay from the flight records.

The analysis helps identify the overall departure delay pattern in the dataset.


## 3. Average Departure Delay by Airline

This analysis calculates the average departure delay for each airline.

It helps compare the departure delay performance of different airlines.


# Apache Pig Analysis

Five Pig programs were implemented in this project.

## 1. Flights by Airline

This analysis calculates the total number of flights operated by each airline.

The result is sorted in descending order.


## 2. Cancelled Flights by Airline

This analysis identifies the number of cancelled flights for each airline.

### Output

```text
AA      22
WN      20
UA      11
NK      10
DL      9
OO      9
OH      9
F9      7
9E      6
YX      6
AS      5
MQ      4
G4      2
B6      1
HA      1
```


## 3. Diverted Flights by Airline

This analysis calculates the number of diverted flights for each airline.

A diverted flight is a flight that does not reach its originally planned destination.


## 4. Average Delay by Airline

This analysis calculates the average departure delay for each airline.

The results help compare the average delay performance among different airlines.


## 5. Delay Causes Analysis

This analysis examines the major causes of flight delays.

The following delay categories were analyzed:

* Carrier Delay
* Weather Delay
* NAS Delay
* Security Delay
* Late Aircraft Delay

### Output

```text
Late Aircraft Delay     61422.0
Carrier Delay           48709.0
NAS Delay               30271.0
Weather Delay           10831.0
Security Delay          88.0
```

The result shows that **Late Aircraft Delay** contributed the highest total delay time in the analyzed dataset.


# Project Structure

```text
Flight-Data-Analysis-Hadoop
│
├── Dataset
│
├── MapReduce
│   │
│   ├── TotalFlightsByAirline
│   │   ├── DriverClass.java
│   │   ├── MapperClass.java
│   │   ├── ReducerClass.java
│   │   └── TotalFlightsByAirline.jar
│   │
│   ├── AverageDepartureDelay
│   │   ├── DriverClass.java
│   │   ├── MapperClass.java
│   │   ├── ReducerClass.java
│   │   └── AverageDepartureDelay.jar
│   │
│   └── AverageDepartureDelayByAirline
│       ├── DriverClass.java
│       ├── MapperClass.java
│       ├── ReducerClass.java
│       └── AverageDepartureDelayByAirline.jar
│
├── Pig
│   │
│   ├── FlightsByAirline
│   │   ├── flights_by_airline.pig
│   │   └── result.txt
│   │
│   ├── CancelledFlightsByAirline
│   │   ├── cancelled_flights_by_airline.pig
│   │   └── result.txt
│   │
│   ├── DivertedFlightsByAirline
│   │   ├── diverted_flights_by_airline.pig
│   │   └── result.txt
│   │
│   ├── AverageDelayByAirline
│   │   ├── average_delay_by_airline.pig
│   │   └── result.txt
│   │
│   └── DelayCausesAnalysis
│       ├── delay_causes_analysis.pig
│       └── result.txt
│
└── Results
    ├── MapReduce1_TotalFlightsByAirline.txt
    ├── MapReduce2_AverageDepartureDelay.txt
    ├── MapReduce3_AverageDepartureDelayByAirline.txt
    ├── Pig1_FlightsByAirline.txt
    ├── Pig2_CancelledFlightsByAirline.txt
    ├── Pig3_DivertedFlightsByAirline.txt
    ├── Pig4_AverageDelayByAirline.txt
    └── Pig5_DelayCausesAnalysis.txt
```


# HDFS Data Processing

The flight dataset was uploaded to HDFS using the following path:

```text
/flight/flight_data_2024_sample.csv
```

The MapReduce and Pig programs process the data stored in HDFS and generate analysis results.


# Conclusion

This project demonstrates how Apache Hadoop, MapReduce, and Apache Pig can be used to analyze flight data.

The analysis provides information about airline flight volumes, departure delays, cancellations, diversions, and major causes of delays.

The project demonstrates the practical use of distributed data storage and processing using Hadoop and Pig.
