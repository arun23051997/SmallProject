package com.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieflix.auth.entity.Users;


@Repository
public interface UserRepo extends JpaRepository<Users,Long>{

	 Optional <Users> findByUsername(String username);

}
