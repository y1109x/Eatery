package com.eatery.cd.mapper;

import com.eatery.cd.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> findRoleList();
}
