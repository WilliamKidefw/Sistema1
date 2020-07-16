package com.william.sistema1.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.william.sistema1.entidad.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User,Serializable> {

	@Query(value="SELECT count(*) FROM user WHERE usuariouser=?1",nativeQuery = true)
	int exists(String username);
	
	List<User> findByAuthToken(String authToken);
	List<User> findByUsername(String username);
}
