package ReferenceData;

import java.io.Serializable;

public class DataDTOB implements Serializable {
	static final long serialVersionUID = 498413215;
	
	private String head;
	private String content;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "DataDTOB [head=" + head + ", content=" + content + "]";
	}
	
}
