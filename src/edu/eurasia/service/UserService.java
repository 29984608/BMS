package edu.eurasia.service;

import java.util.List;

import edu.eurasia.entity.UsersBean;

public interface UserService {
	
	// ����Ա��¼
	List<UsersBean> login(UsersBean users) throws Exception;

}
