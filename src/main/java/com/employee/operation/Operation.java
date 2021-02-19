//$Id$
package com.employee.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operation {
	private long recordId;
	private String firstOff;
	private String productLane;
	private long partNumber;
	private long timeIn;
	private long timeOut;
	private String status;
	private long duration;
	
	@JsonProperty("FIRST_OFF")
	public String getFirstOff() {
		return firstOff;
	}
	public void setFirstOff(String firstOff) {
		this.firstOff = firstOff;
	}
	
	@JsonProperty("PRODUCT_LANE")
	public String getProductLane() {
		return productLane;
	}
	public void setProductLane(String productLane) {
		this.productLane = productLane;
	}
	
	@JsonProperty("PART_NUMBER")
	public long getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(long partNumber) {
		this.partNumber = partNumber;
	}
	
	@JsonProperty("TIME_IN")
	public long getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(long timeIn) {
		this.timeIn = timeIn;
	}
	
	@JsonProperty("TIME_OUT")
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	
	@JsonProperty("STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty("DURATION")
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
}
