package com.nemanja.userapp.util;

import com.nemanja.userapp.data.model.User;

import java.util.List;


public interface OnCallbackRecieved {
    void returnList(List<User> list);
}
