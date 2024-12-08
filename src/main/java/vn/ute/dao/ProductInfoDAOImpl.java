package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.ProductInfo;
@Repository
@Transactional(rollbackFor=Exception.class)
public class ProductInfoDAOImpl extends BaseDAOImpl<ProductInfo> implements ProductInfoDAO<ProductInfo>{

}
