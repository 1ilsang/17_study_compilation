package polymorphism;

import java.util.Set;

//import java.util.List;

public class CollectionBean {
	/*//중복 값을 허용하는 객체 집합.
	 * private List<String> addressList;
	
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;		
	}
	public List<String> getAddressList() {
		return addressList;
	}*/
	
	//중복 값을 허용하지 않는 객체 집합
	private Set<String> addressList;

	public void setAddressList(Set<String> addressList) {
		this.addressList = addressList;
	}

	public Set<String> getAddressList() {
		return addressList;
	}
	
}
