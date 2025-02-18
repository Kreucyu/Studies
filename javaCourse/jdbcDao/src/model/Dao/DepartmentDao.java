package model.Dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert(Department obj);
	void update(Department obj, Integer firstId);
	void deleteById(Integer id);
	Department findById(Integer obj);
	List<Department> findAll();

}
