package vn.ute.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ute.entity.Category;
import vn.ute.entity.Invoice;
@Repository
@Transactional(rollbackFor=Exception.class)
public class InvoiceDAOImpl extends BaseDAOImpl<Invoice> implements InvoiceDAO<Invoice>{

}
