package org.qualipso.factory.ui.service.oslc.client.model;

import java.io.Serializable;

/**
 * 
 * @author Huihui Yang
 * 
 *
 */
public class Results implements Serializable{

    private static final long serialVersionUID = 1L;
    private int srcCount;
    private int licensesCount;
    private int unknownCount;
    private int disLicensesCount;
    private int confRefCount; 
    private int confGblCount;
    private int allCount;
    private int copyRightHolders;
    private int copyRightedFiles;
    
   
    //private String allFilesArray;
    private String conflictingFiles;
   // private String LicensedFiles;
    private String UncertainLicensesFiles;
    private String MissingLicenseFiles;
	public int getSrcCount() {
		return srcCount;
	}
	public void setSrcCount(int srcCount) {
		this.srcCount = srcCount;
	}
	public int getLicensesCount() {
		return licensesCount;
	}
	public void setLicensesCount(int licensesCount) {
		this.licensesCount = licensesCount;
	}
	public int getUnknownCount() {
		return unknownCount;
	}
	public void setUnknownCount(int unknownCount) {
		this.unknownCount = unknownCount;
	}
	public int getDisLicensesCount() {
		return disLicensesCount;
	}
	public void setDisLicensesCount(int disLicensesCount) {
		this.disLicensesCount = disLicensesCount;
	}
	public int getConfRefCount() {
		return confRefCount;
	}
	public void setConfRefCount(int confRefCount) {
		this.confRefCount = confRefCount;
	}
	public int getConfGblCount() {
		return confGblCount;
	}
	public void setConfGblCount(int confGblCount) {
		this.confGblCount = confGblCount;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getCopyRightHolders() {
		return copyRightHolders;
	}
	public void setCopyRightHolders(int copyRightHolders) {
		this.copyRightHolders = copyRightHolders;
	}
	public int getCopyRightedFiles() {
		return copyRightedFiles;
	}
	public void setCopyRightedFiles(int copyRightedFiles) {
		this.copyRightedFiles = copyRightedFiles;
	}
	public String getConflictingFiles() {
		return conflictingFiles;
	}
	public void setConflictingFiles(String conflictingFiles) {
		this.conflictingFiles = conflictingFiles;
	}
	public String getUncertainLicensesFiles() {
		return UncertainLicensesFiles;
	}
	public void setUncertainLicensesFiles(String uncertainLicensesFiles) {
		UncertainLicensesFiles = uncertainLicensesFiles;
	}
	public String getMissingLicenseFiles() {
		return MissingLicenseFiles;
	}
	public void setMissingLicenseFiles(String missingLicenseFiles) {
		MissingLicenseFiles = missingLicenseFiles;
	}
    

    
}

