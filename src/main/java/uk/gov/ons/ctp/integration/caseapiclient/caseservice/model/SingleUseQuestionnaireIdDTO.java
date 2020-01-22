package uk.gov.ons.ctp.integration.caseapiclient.caseservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.Data;

/** This class holds data for a newly allocated questionnaire ID. */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUseQuestionnaireIdDTO {

  private String questionnaireId;
  private String uac;
}
