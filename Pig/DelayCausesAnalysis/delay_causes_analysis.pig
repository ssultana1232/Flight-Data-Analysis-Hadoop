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

carrier = FOREACH flights GENERATE
    'Carrier Delay' AS cause,
    carrier_delay AS delay;

weather = FOREACH flights GENERATE
    'Weather Delay' AS cause,
    weather_delay AS delay;

nas = FOREACH flights GENERATE
    'NAS Delay' AS cause,
    nas_delay AS delay;

security = FOREACH flights GENERATE
    'Security Delay' AS cause,
    security_delay AS delay;

late_aircraft = FOREACH flights GENERATE
    'Late Aircraft Delay' AS cause,
    late_aircraft_delay AS delay;

all_causes = UNION carrier, weather, nas, security, late_aircraft;

grouped = GROUP all_causes BY cause;

result = FOREACH grouped GENERATE
    group AS delay_cause,
    SUM(all_causes.delay) AS total_delay_minutes;

ordered = ORDER result BY total_delay_minutes DESC;

STORE ordered INTO '/flight/pig_delay_causes_analysis'
    USING PigStorage('\t');