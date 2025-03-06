/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;

/**
 *
 * @author tiend
 */
public class PdfLis {

    private int PdfID;
    private String PdfName;
    private int AssetID;
    private int SalaryID;

    public PdfLis() {
    }

    public PdfLis(String PdfName, int AssetID, int SalaryID) {
        this.PdfName = PdfName;
        this.AssetID = AssetID;
        this.SalaryID = SalaryID;
    }

    public PdfLis(int PdfID, String PdfName, int AssetID, int SalaryID) {
        this.PdfID = PdfID;
        this.PdfName = PdfName;
        this.AssetID = AssetID;
        this.SalaryID = SalaryID;
    }
    
    public int getPdfID() {
        return PdfID;
    }

    public void setPdfID(int PdfID) {
        this.PdfID = PdfID;
    }

    public String getPdfName() {
        return PdfName;
    }

    public void setPdfName(String PdfName) {
        this.PdfName = PdfName;
    }

    public int getAssetID() {
        return AssetID;
    }

    public void setAssetID(int AssetID) {
        this.AssetID = AssetID;
    }

    public int getSalaryID() {
        return SalaryID;
    }

    public void setSalaryID(int SalaryID) {
        this.SalaryID = SalaryID;
    }
    @Override
    public String toString() {
        return "PdfLis{" + "PdfID=" + PdfID + ", PdfName=" + PdfName + ", AssetID=" + AssetID + ", SalaryID=" + SalaryID + '}';
    }

}
