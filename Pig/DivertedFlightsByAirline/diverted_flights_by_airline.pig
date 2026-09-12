DEFINE CSVLoader org.apache.pig.piggybank.storage.CSVExcelStorage();

flights = LOAD '/flight/flight_data_2024_sample.csv'
    USING CSVLoader()
    AS (
        year:int,
        month:int,
        day_of_month:int,
        day_of_week:int,
        fl_date:chararray,
        op_unique_carrier:chararray,
        op_carrier_fl_num:double,
        origin:chararray,
        origin_city_name:chararray,
        origin_state_nm:chararray,
        dest:chararray,
        dest_city_name:chararray,
        dest_state_nm:chararray,
        crs_dep_time:int,
        dep_time:double,
        dep_delay:double,
        taxi_out:double,
        wheels_off:double,
        wheels_on:double,
        taxi_in:double,
        crs_arr_time:int,
        arr_time:double,
        arr_delay:double,
        cancelled:double,
        cancellation_code:chararray,
        diverted:double,
        crs_elapsed_time:double,
        actual_elapsed_time:double,
        air_time:double,
        distance:double,
        carrier_delay:double,
        weather_delay:double,
        nas_delay:double,
        security_delay:double,
        late_aircraft_delay:double
    );

flights = FILTER flights BY op_unique_carrier != 'op_unique_carrier';

diverted_flights = FILTER flights BY diverted == 1;

grouped = GROUP diverted_flights BY op_unique_carrier;

result = FOREACH grouped GENERATE
    group AS airline,
    COUNT(diverted_flights) AS diverted_flights;

ordered = ORDER result BY diverted_flights DESC;

STORE ordered INTO '/flight/pig_diverted_flights_by_airline'
    USING PigStorage('\t');