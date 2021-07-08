package com.mdl.hundun.repository;

import com.mdl.hundun.entity.CoursePptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePptRepository extends JpaRepository<CoursePptEntity,String> {


}
