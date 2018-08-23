package com.augustars.xmall.service;

import com.augustars.xmall.entity.User;

public interface UserService {
	public User getUserByLogibName(String loginName) throws Exception;

}
