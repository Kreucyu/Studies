package model.Dao;

import db.DB;
import model.Dao.impl.DepartmentDaoJDBC;
import model.Dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
		
	}
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
		
	}

}
