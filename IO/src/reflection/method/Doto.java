package reflection.method;

public class Doto {
	private String head;
	private String body;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void cry() {
		System.out.println("why???");
	}

	public String toString() {
		return "Doto [head=" + head + ", body=" + body + "]";
	}
	
	

}
