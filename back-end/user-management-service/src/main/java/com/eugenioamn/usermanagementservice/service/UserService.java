package com.eugenioamn.usermanagementservice.service;

import com.eugenioamn.usermanagementservice.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getByUsername(String username);

    List<String> getByIdList(List<Long> idList);
}
