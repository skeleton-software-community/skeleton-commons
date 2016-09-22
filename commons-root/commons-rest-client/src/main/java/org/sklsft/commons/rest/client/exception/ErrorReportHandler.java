package org.sklsft.commons.rest.client.exception;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.sklsft.commons.api.exception.ApplicationException;
import org.sklsft.commons.api.exception.ErrorReport;
import org.sklsft.commons.api.exception.TechnicalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;


/**
 * this class is responsible for handling error reports that a rest service can respond.<br/>
 * it works as a convertor from a  {@link ErrorReport} to a {@link ApplicationException}
 * 
 * @author Nicolas Thibault
 *
 */
public class ErrorReportHandler implements ResponseErrorHandler {
	
	private ObjectMapper objectMapper;
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#hasError(org.springframework.http.client.ClientHttpResponse)
	 */
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return !response.getStatusCode().equals(HttpStatus.OK);
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#handleError(org.springframework.http.client.ClientHttpResponse)
	 */
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		ErrorReport errorReport = objectMapper.readValue(response.getBody(), ErrorReport.class);
		convertErrorReport(errorReport);
		
	}

	
	/**
	 * instantiates the {@link ApplicationException} as described in the {@link ErrorReport} that a rest service can respond
	 * @param errorReport
	 */
	private void convertErrorReport(ErrorReport errorReport) {
		
		ApplicationException exception;
		
		try {
			exception = (ApplicationException) Class.forName(errorReport.getExceptionClassName()).newInstance();
			exception.setMessage(errorReport.getMessage());
		} catch (Exception e) {
			exception = new TechnicalError(ApplicationException.ERROR_UNKNOWN, e);
		}
		throw exception;
	}

}