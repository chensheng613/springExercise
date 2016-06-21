package com.mypro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mypro.entity.AccessToken;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

	public AccessToken findByaccessToken(String accessToken);
}
