package com.rfb.repository;

import com.rfb.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

}

