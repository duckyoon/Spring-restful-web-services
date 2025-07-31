package com.junes.rest.webservices.restful_web_services.jpa;

import com.junes.rest.webservices.restful_web_services.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 는 User Entity를 관리하고 id 는 integer
public interface PostRepository extends JpaRepository<Post, Integer> {

}
