package models;

import java.util.List;

public class IdsRequirementsAttributes {

    public List<String> projectChapters;
    public String objectKinds;
    public String fnoCodes;
    public List<String> groups;

    public IdsRequirementsAttributes(List<String> projectChapters, String objectKinds, String fnoCodes,
                                     List<String> groups){
        this.projectChapters = projectChapters;
        this.objectKinds = objectKinds;
        this.fnoCodes = fnoCodes;
        this.groups = groups;
    }

    public List<String> getProjectChapters() {
        return projectChapters;
    }

    public String getObjectKinds() {
        return objectKinds;
    }

    public String getFnoCodes() {
        return fnoCodes;
    }

    public List<String> getGroups() {
        return groups;
    }
}
