package vmn.tve.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ProvidersListDTO object converted to json and returned to centralized service.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersListDTO extends ResponseDTO {
    private List<ProviderDTO> providersList;

    /**
     * @return providers list
     */
    public List<ProviderDTO> getProvidersList() {
        if (providersList == null) {
            providersList = new ArrayList<ProviderDTO>();
        }
        return providersList;
    }

    public void setProvidersList(final List<ProviderDTO> providersList) {
        this.providersList = providersList;
    }

}
