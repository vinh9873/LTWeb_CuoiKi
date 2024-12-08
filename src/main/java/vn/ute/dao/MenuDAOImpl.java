package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.Menu;
import vn.ute.entity.Role;
@Repository
@Transactional(rollbackFor=Exception.class)
public class MenuDAOImpl extends BaseDAOImpl<Menu> implements MenuDAO<Menu>{

}
