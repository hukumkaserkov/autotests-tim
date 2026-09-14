package models;

import java.util.List;

public class CreateNewIds {

    public String requirementsFileId;
    public IdsRequirementsAttributes idsRequirementsAttributes;
    public Boolean useInCheck;

    public CreateNewIds(String requirementsFileId, IdsRequirementsAttributes idsRequirementsAttributes,
                        Boolean useInCheck){
        this.requirementsFileId = requirementsFileId;
        this.idsRequirementsAttributes = idsRequirementsAttributes;
        this.useInCheck = useInCheck;
    }

    public String getRequirementsFileId() {
        return requirementsFileId;
    }

    public IdsRequirementsAttributes getIdsRequirementsAttributes() {
        return idsRequirementsAttributes;
    }

    public Boolean getUseInCheck() {
        return useInCheck;
    }
}
