package com.uxpsystems.assignment.repository;

import com.uxpsystems.assignment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
