package lk.gov.health.hpb.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticsData {

    @SerializedName("daily_pcr_testing_data")
    private List<DailyPcrTestingData> dailyPcrTestingData;
    @SerializedName("global_deaths")
    private long globalDeaths;
    @SerializedName("global_new_cases")
    private long globalNewCases;
    @SerializedName("global_new_deaths")
    private long globalNewDeaths;
    @SerializedName("global_recovered")
    private long globalRecovered;
    @SerializedName("global_total_cases")
    private long globalTotalCases;
    @SerializedName("hospital_data")
    private List<HospitalData> hospitalData;
    @SerializedName("local_active_cases")
    private long localActiveCases;
    @SerializedName("local_deaths")
    private long localDeaths;
    @SerializedName("local_new_cases")
    private long localNewCases;
    @SerializedName("local_new_deaths")
    private long localNewDeaths;
    @SerializedName("local_recovered")
    private long localRecovered;
    @SerializedName("local_total_cases")
    private long localTotalCases;
    @SerializedName("local_total_number_of_individuals_in_hospitals")
    private long localTotalNumberOfIndividualsInHospitals;
    @SerializedName("total_pcr_testing_count")
    private long totalPcrTestingCount;
    @SerializedName("update_date_time")
    private String updateDateTime;

}
