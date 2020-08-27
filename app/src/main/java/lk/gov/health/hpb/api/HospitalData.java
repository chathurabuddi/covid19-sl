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
