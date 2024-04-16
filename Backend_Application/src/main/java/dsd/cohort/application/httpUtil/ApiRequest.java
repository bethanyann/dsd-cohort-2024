package dsd.cohort.application.httpUtil;

import lombok.Data;

@Data
public class ApiRequest {

  private String baseUrl;

  public ApiRequest(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String buildUrl(String pathVariables) {
    return baseUrl + (pathVariables.length() > 0 ? "/" + pathVariables : "");
  }

  

}
