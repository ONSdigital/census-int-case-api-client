package uk.gov.ons.ctp.integration.caseapiclient.config;

import lombok.Data;
import uk.gov.ons.ctp.common.rest.RestClientConfig;

@Data
public class AddressIndexSettings {
  private String addressQueryPath;
  private String postcodeLookupPath;
  private RestClientConfig restClientConfig;
}
