package com.nemanja.userapp.util;

import com.nemanja.userapp.data.model.User;

import java.util.List;

/**
 * Created by Vladan on 1.12.2017..
 */

public interface OnCallbackRecieved {
    public void returnList(List<User> list);
}
