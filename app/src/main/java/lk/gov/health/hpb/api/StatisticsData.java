package lk.gov.health.hpb.api;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chathura Buddhika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
