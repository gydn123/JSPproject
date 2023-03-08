package dto;

public class MemberOrder {
	
	String orderId = "";
	String addressee = "";
	String id = "";
	String memberAddr = "";
	String p_id = "";
	String name = "";
	
	public MemberOrder() {
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	@Override
	public String toString() {
		return "MemberOrder [orderId=" + orderId + ", addressee=" + addressee + ", id=" + id + ", memberAddr="
				+ memberAddr + ", p_id=" + p_id + ", name=" + name + "]";
	}

}
