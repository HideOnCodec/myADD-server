package com.myadd.myadd.user.sigunup.email.change.profile.service;

import com.myadd.myadd.user.domain.UserEntity;
import com.myadd.myadd.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeProfileService {

    @Autowired
    private final UserRepository userRepository;


}
