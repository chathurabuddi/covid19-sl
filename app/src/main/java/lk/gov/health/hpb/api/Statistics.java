package lk.gov.health.hpb.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Statistics {

    private StatisticsData data;
    private String message;
    private Boolean success;

}
