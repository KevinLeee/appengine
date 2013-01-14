package oop.kevin.clients.datasync.webservice.soap.response;

import javax.xml.bind.annotation.XmlType;

import oop.kevin.clients.datasync.webservice.soap.WsConstants;
import oop.kevin.clients.datasync.webservice.soap.response.base.WSResult;
import oop.kevin.clients.datasync.webservice.soap.response.dto.UserDTO;

@XmlType(name = "GetUserResult", namespace = WsConstants.NS)
public class GetUserResult extends WSResult {
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
