package com.aarria.retail.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AppProperties {

	@Value("${static.files.host.realpath.root}")
	public String staticFilesRootPath;

	@Value("${static.host.url}")
	public String cdnUrl;

	@Value("${cdn.private.key.file.location}")
	private String cdnPrivateKeyLocation;

	@Value("${static.host.resources.url}")
	public String cdnResources;

	@Value("${sms.gateway.uri.promotional}")
	public String smsGatwayUriPromotional;

	@Value("${sms.gateway.uri.otp}")
	public String smsGatwayUriOTP;

	@Value("${turn.off.sms}")
	public Boolean isTurnOffSms;
	
	public String getStaticFilesRootPath() {
		return staticFilesRootPath;
	}

	public void setStaticFilesRootPath(String staticFilesRootPath) {
		this.staticFilesRootPath = staticFilesRootPath;
	}

	public String getCdnUrl() {
		return cdnUrl;
	}

	public void setCdnUrl(String cdnUrl) {
		this.cdnUrl = cdnUrl;
	}

	public String getCdnPrivateKeyLocation() {
		return cdnPrivateKeyLocation;
	}

	public void setCdnPrivateKeyLocation(String cdnPrivateKeyLocation) {
		this.cdnPrivateKeyLocation = cdnPrivateKeyLocation;
	}

	public String getCdnResources() {
		return cdnResources;
	}

	public void setCdnResources(String cdnResources) {
		this.cdnResources = cdnResources;
	}

	public String getSmsGatwayUriPromotional() {
		return smsGatwayUriPromotional;
	}

	public void setSmsGatwayUriPromotional(String smsGatwayUriPromotional) {
		this.smsGatwayUriPromotional = smsGatwayUriPromotional;
	}

	public String getSmsGatwayUriOTP() {
		return smsGatwayUriOTP;
	}

	public void setSmsGatwayUriOTP(String smsGatwayUriOTP) {
		this.smsGatwayUriOTP = smsGatwayUriOTP;
	}

	public Boolean getIsTurnOffSms() {
		return isTurnOffSms;
	}

	public void setIsTurnOffSms(Boolean isTurnOffSms) {
		this.isTurnOffSms = isTurnOffSms;
	}
	
}
