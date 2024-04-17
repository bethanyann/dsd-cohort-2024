package dsd.cohort.application.config;

import org.springframework.stereotype.Service;

@Service
public interface ApiDetails {

  /**
   * This interface defines the API details.
   * 
   * Create a new java file that implements this interface named ApiDetailsImpl.java
   * 
   * When implementing this interface, the following variables must be defined with your API details:
   * @var apiId
   * @var apiKey
   * 
   * @return "?app_id=" + this.apiId + "&app_key=" + this.apiKey + "&type=public";
   * 
   * This will allow you to access the API with your credentials while hiding the API details.
   */

  String getApiDetails();

}
