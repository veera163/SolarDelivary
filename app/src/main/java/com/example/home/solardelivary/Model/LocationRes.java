package com.example.home.solardelivary.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by home on 12/15/2017.
 */

public class LocationRes {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("doorNum")
    @Expose
    private Object doorNum;
    @SerializedName("street")
    @Expose
    private Object street;
    @SerializedName("area")
    @Expose
    private Object area;
    @SerializedName("cityTown")
    @Expose
    private Object cityTown;
    @SerializedName("district")
    @Expose
    private Object district;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("mandal")
    @Expose
    private Object mandal;
    @SerializedName("latLng")
    @Expose
    private String latLng;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("capturedBy")
    @Expose
    private Object capturedBy;

    @SerializedName("deliveryStatus")
    @Expose
    private String deliveryStatus;
    @SerializedName("materialDelivered")
    @Expose
    private List<String> materialDelivered = null;
    @SerializedName("installationStatus")
    @Expose
    private String installationStatus;
    @SerializedName("deliveryDigitalSigns")
    @Expose
    private Object deliveryDigitalSigns;
    @SerializedName("installationsDigitalSigns")
    @Expose
    private Object installationsDigitalSigns;
    @SerializedName("schoolCode")
    @Expose
    private String schoolCode;
    @SerializedName("loc")
    @Expose
    private List<Double> loc = null;
    @SerializedName("type")
    @Expose
    private Object type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(Object doorNum) {
        this.doorNum = doorNum;
    }

    public Object getStreet() {
        return street;
    }

    public void setStreet(Object street) {
        this.street = street;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public Object getCityTown() {
        return cityTown;
    }

    public void setCityTown(Object cityTown) {
        this.cityTown = cityTown;
    }

    public Object getDistrict() {
        return district;
    }

    public void setDistrict(Object district) {
        this.district = district;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getMandal() {
        return mandal;
    }

    public void setMandal(Object mandal) {
        this.mandal = mandal;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Object getCapturedBy() {
        return capturedBy;
    }

    public void setCapturedBy(Object capturedBy) {
        this.capturedBy = capturedBy;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<String> getMaterialDelivered() {
        return materialDelivered;
    }

    public void setMaterialDelivered(List<String> materialDelivered) {
        this.materialDelivered = materialDelivered;
    }

    public String getInstallationStatus() {
        return installationStatus;
    }

    public void setInstallationStatus(String installationStatus) {
        this.installationStatus = installationStatus;
    }

    public Object getDeliveryDigitalSigns() {
        return deliveryDigitalSigns;
    }

    public void setDeliveryDigitalSigns(Object deliveryDigitalSigns) {
        this.deliveryDigitalSigns = deliveryDigitalSigns;
    }

    public Object getInstallationsDigitalSigns() {
        return installationsDigitalSigns;
    }

    public void setInstallationsDigitalSigns(Object installationsDigitalSigns) {
        this.installationsDigitalSigns = installationsDigitalSigns;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public List<Double> getLoc() {
        return loc;
    }

    public void setLoc(List<Double> loc) {
        this.loc = loc;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}