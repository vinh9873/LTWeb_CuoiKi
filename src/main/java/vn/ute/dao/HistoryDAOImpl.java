package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.Category;
import vn.ute.entity.History;
@Repository
@Transactional(rollbackFor=Exception.class)
public class HistoryDAOImpl extends BaseDAOImpl<History> implements HistoryDAO<History>{

}
