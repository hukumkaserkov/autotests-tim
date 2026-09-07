package models;

import java.time.Instant;

public class CreateAppeal {

    private String addressType;
    private String appealDate;
    private String appealNumber;
    private ObjectKind objectKind;
    private String objectName;
    private String reqVersion;

    public CreateAppeal(String addressType, ObjectKind objectKind, String objectName, String reqVersion) {
        this.addressType = addressType;
        this.appealDate = getCurrentDate();
        this.appealNumber = generateAppealNumber();
        this.objectKind = objectKind;
        this.objectName = objectName;
        this.reqVersion = reqVersion;
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    private String generateAppealNumber() {
        return "Я Авто Тест" + System.currentTimeMillis();
    }

    public String getAddressType() {
        return addressType; }
    public String getAppealDate() {
        return appealDate; }
    public String getAppealNumber() {
        return appealNumber; }
    public String getObjectName() {
        return objectName; }
    public String getReqVersion() {
        return reqVersion; }
    public ObjectKind getObjectKind() {
        return objectKind; }
}
