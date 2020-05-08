package lk.gov.health.hpb.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalData {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("cumulative_foreign")
    private long cumulativeForeign;
    @SerializedName("cumulative_local")
    private long cumulativeLocal;
    @SerializedName("cumulative_total")
    private long cumulativeTotal;
    @SerializedName("deleted_at")
    private Object deletedAt;
    private Hospital hospital;
    @SerializedName("hospital_id")
    private long hospitalId;
    private long id;
    @SerializedName("treatment_foreign")
    private long treatmentForeign;
    @SerializedName("treatment_local")
    private long treatmentLocal;
    @SerializedName("treatment_total")
    private long treatmentTotal;
    @SerializedName("updated_at")
    private Object updatedAt;

}
