package vn.ute.dao;

import vn.ute.entity.Auth;

public interface AuthDAO<E> extends BaseDAO<E> {
	public Auth find(int roleId, int menuId);
}
