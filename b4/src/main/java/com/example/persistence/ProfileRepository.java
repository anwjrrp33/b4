package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, String> {

}
