package lk.gov.health.hpb.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hospital {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("deleted_at")
    private Object deletedAt;
    private long id;
    private String name;
    @SerializedName("name_si")
    private String nameSi;
    @SerializedName("name_ta")
    private String nameTa;
    @SerializedName("updated_at")
    private String updatedAt;

}
