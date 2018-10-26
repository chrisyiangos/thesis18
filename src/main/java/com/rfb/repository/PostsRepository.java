package com.rfb.repository;

import com.rfb.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
