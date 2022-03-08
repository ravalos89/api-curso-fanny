package libraryscenarios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponse {
	
	private String msg, id;
	
	@JsonProperty("Msg")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@JsonProperty("ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
