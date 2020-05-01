package com.app.idnbin.LoginRegister;

import java.io.Serializable;

public class DeviceDetails implements Serializable {

    private String android_id, device_id, imei, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model,
            device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental;

    public DeviceDetails(String android_id, String device_id, String imei, String meid, String serialNumber, String networkCountryIso, String simCountryIso, String networkOperatorName, String simOperatorName, String line1Number, String model, String device, String brand_Id, String manufacturer, String product, String type, String host, String hardware, String base_OS, String codename, String release, String security_Patch, String incrimental) {
        this.android_id = android_id;
        this.device_id = device_id;
        this.imei = imei;
        this.meid = meid;
        this.serialNumber = serialNumber;
        this.networkCountryIso = networkCountryIso;
        this.simCountryIso = simCountryIso;
        this.networkOperatorName = networkOperatorName;
        this.simOperatorName = simOperatorName;
        this.line1Number = line1Number;
        this.model = model;
        this.device = device;
        this.brand_Id = brand_Id;
        this.manufacturer = manufacturer;
        this.product = product;
        this.type = type;
        this.host = host;
        this.hardware = hardware;
        this.base_OS = base_OS;
        this.codename = codename;
        this.release = release;
        this.security_Patch = security_Patch;
        this.incrimental = incrimental;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNetworkCountryIso() {
        return networkCountryIso;
    }

    public void setNetworkCountryIso(String networkCountryIso) {
        this.networkCountryIso = networkCountryIso;
    }

    public String getSimCountryIso() {
        return simCountryIso;
    }

    public void setSimCountryIso(String simCountryIso) {
        this.simCountryIso = simCountryIso;
    }

    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getLine1Number() {
        return line1Number;
    }

    public void setLine1Number(String line1Number) {
        this.line1Number = line1Number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrand_Id() {
        return brand_Id;
    }

    public void setBrand_Id(String brand_Id) {
        this.brand_Id = brand_Id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getBase_OS() {
        return base_OS;
    }

    public void setBase_OS(String base_OS) {
        this.base_OS = base_OS;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getSecurity_Patch() {
        return security_Patch;
    }

    public void setSecurity_Patch(String security_Patch) {
        this.security_Patch = security_Patch;
    }

    public String getIncrimental() {
        return incrimental;
    }

    public void setIncrimental(String incrimental) {
        this.incrimental = incrimental;
    }
}
