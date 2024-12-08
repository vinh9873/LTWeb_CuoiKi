package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.UserRole;
import vn.ute.entity.UserWeb;
@Repository
@Transactional(rollbackFor=Exception.class)
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole>  implements UserRoleDAO<UserRole>{

}
