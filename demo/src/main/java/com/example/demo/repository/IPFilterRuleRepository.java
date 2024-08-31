package com.example.demo.repository;

import com.example.demo.entity.IPFilterRule;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IPFilterRuleRepository extends JpaRepository<IPFilterRule, Long> {
}
