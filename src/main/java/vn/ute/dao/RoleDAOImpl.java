package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.Category;
import vn.ute.entity.Role;
@Repository
@Transactional(rollbackFor=Exception.class)
public class RoleDAOImpl extends BaseDAOImpl<Role> implements RoleDAO<Role>{

}
