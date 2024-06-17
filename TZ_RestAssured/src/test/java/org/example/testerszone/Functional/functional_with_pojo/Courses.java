package org.example.testerszone.Functional.functional_with_pojo;

import java.util.List;

public class Courses {
    private List<WebAutomation> webAutomation;
    private List <Api> api;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List <Api> getApi() {
        return api;
    }

    public void setApi(List <Api> api) {
        this.api = api;
    }
}
