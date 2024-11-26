package geocontroller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunriseSunset (String sunrise, String sunset) {
}
