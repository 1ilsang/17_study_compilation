package polymorphism;

import java.util.Set;

//import java.util.List;

public class CollectionBean {
	/*//�ߺ� ���� ����ϴ� ��ü ����.
	 * private List<String> addressList;
	
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;		
	}
	public List<String> getAddressList() {
		return addressList;
	}*/
	
	//�ߺ� ���� ������� �ʴ� ��ü ����
	private Set<String> addressList;

	public void setAddressList(Set<String> addressList) {
		this.addressList = addressList;
	}

	public Set<String> getAddressList() {
		return addressList;
	}
	
}
