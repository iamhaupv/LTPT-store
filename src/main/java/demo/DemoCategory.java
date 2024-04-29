package demo;

import dao.CategoryDao;
import entity.Category;

public class DemoCategory {
	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.getAll(Category.class).forEach(e->System.out.println(e));
	}
}
