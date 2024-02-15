package com.anys34.homework.domain.user.service;

import com.anys34.homework.domain.user.domain.User;
import com.anys34.homework.domain.user.presentation.dto.UserInfoResponse;
import com.anys34.homework.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginUserInfoService {
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserInfoResponse execute() {
        User user = userFacade.getCurrentUser();
        return new UserInfoResponse(user);
    }
}
