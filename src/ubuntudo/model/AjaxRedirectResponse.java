package ubuntudo.model;

public class AjaxRedirectResponse {
	private String status;
	private String uri;
	
	public AjaxRedirectResponse() {
	}
	
	public AjaxRedirectResponse(String status, String uri) {
		this.status = status;
		this.uri = uri;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
