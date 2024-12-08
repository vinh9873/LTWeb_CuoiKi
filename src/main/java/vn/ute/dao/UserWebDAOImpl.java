package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.UserWeb;
@Repository
@Transactional(rollbackFor=Exception.class)
public class UserWebDAOImpl extends BaseDAOImpl<UserWeb>  implements UserDAO<UserWeb>{

}
