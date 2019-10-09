package uk.gov.ons.ctp.integration.caseapiclient.caseservice;

import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggerFactory;
import java.util.List;
import java.util.UUID;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.gov.ons.ctp.common.rest.RestClient;
import uk.gov.ons.ctp.integration.caseapiclient.caseservice.model.CaseContainerDTO;

/** This class is responsible for communications with the Case Service. */
public class CaseServiceClientServiceImpl {
  private static final Logger log = LoggerFactory.getLogger(CaseServiceClientServiceImpl.class);
  private static final String CASE_BY_ID_QUERY_PATH = "/cases/{case-id}";
  private static final String CASE_BY_UPRN_QUERY_PATH = "/cases/uprn/{uprn}";
  private static final String CASE_BY_CASE_REFERENCE_QUERY_PATH = "/cases/ref/{reference}";

  private RestClient caseServiceClient;

  public CaseServiceClientServiceImpl(RestClient caseServiceClient) {
    super();
    this.caseServiceClient = caseServiceClient;
  }

  public CaseContainerDTO getCaseById(UUID caseId, Boolean listCaseEvents) {
    log.with("caseId", caseId)
        .debug("getCaseById() calling Case Service to find case details by ID");

    // Build map for query params
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("caseEvents", Boolean.toString(listCaseEvents));

    // Ask Case Service to find case details
    CaseContainerDTO caseDetails =
        caseServiceClient.getResource(
            CASE_BY_ID_QUERY_PATH, CaseContainerDTO.class, null, queryParams, caseId.toString());
    log.with("caseId", caseId).debug("getCaseById() found case details for case ID");

    return caseDetails;
  }

  public List<CaseContainerDTO> getCaseByUprn(Long uprn, Boolean listCaseEvents) {
    log.with("uprn", uprn)
        .debug("getCaseByUprn() calling Case Service to find case details by Uprn");

    // Build map for query params
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("caseEvents", Boolean.toString(listCaseEvents));

    // Ask Case Service to find case details
    List<CaseContainerDTO> cases =
        caseServiceClient.getResources(
            CASE_BY_UPRN_QUERY_PATH,
            CaseContainerDTO[].class,
            null,
            queryParams,
            Long.toString(uprn));

    log.with("uprn", uprn).debug("getCaseByUprn() found case details by Uprn");

    return cases;
  }

  public CaseContainerDTO getCaseByCaseRef(Long caseReference, Boolean listCaseEvents) {
    log.with("caseReference", caseReference)
        .debug(
            "getCaseByCaseReference() calling Case Service to find case details by case reference");

    // Build map for query params
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("caseEvents", Boolean.toString(listCaseEvents));

    // Ask Case Service to find case details
    CaseContainerDTO caseDetails =
        caseServiceClient.getResource(
            CASE_BY_CASE_REFERENCE_QUERY_PATH,
            CaseContainerDTO.class,
            null,
            queryParams,
            caseReference);

    log.with("caseReference", caseReference)
        .debug("getCaseByCaseReference() found case details by case reference");

    return caseDetails;
  }
}
