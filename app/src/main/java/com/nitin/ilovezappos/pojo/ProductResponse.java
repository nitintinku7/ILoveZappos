package com.nitin.ilovezappos.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nitin on 02-10-2017.
 */

public class ProductResponse {
    @SerializedName("originalTerm")
    private String originalTerm;
    @SerializedName("currentResultCount")
    private String currentResultCount;
    @SerializedName("totalResultCount")
    private String totalResultCount;
    @SerializedName("results")
    private List<Product> results;
    @SerializedName("statusCode")
    private String statusCode;

    public ProductResponse(String originalTerm, String currentResultCount, String totalResultCount, List<Product> results, String statusCode) {
        this.setOriginalTerm(originalTerm);
        this.setCurrentResultCount(currentResultCount);
        this.setTotalResultCount(totalResultCount);
        this.setResults(results);
        this.setStatusCode(statusCode);
    }

    public String getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
    }

    public String getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public String getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(String totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
