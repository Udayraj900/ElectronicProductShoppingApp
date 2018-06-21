package com.mindtree.webservices.entity;

public class ErrorResponse {

		private int errorCode;
		private String requestURI;
		private String message;
		public ErrorResponse(String message, String requestURI) {
			
			this.message = message;
			this.requestURI = requestURI;
		}
		public ErrorResponse() {
		}
		
		public int getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
		/**
		 * @return the errorCode
		 */
		public String getRequestURI() {
			return requestURI;
		}
		/**
		 * @param errorCode the errorCode to set
		 */
		public void setRequestURI(String requestURI) {
			this.requestURI = requestURI;
		}
		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	

}
