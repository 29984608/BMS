package edu.eurasia.service;

import java.util.List;

import edu.eurasia.entity.CategoryBean;
import edu.eurasia.entity.UsersBean;

public interface CategoryService {
	
	// ��ѯCategory����������
	List<CategoryBean> selectCategoryAll() throws Exception;

	// ��cnameģ����ѯCategory���Ӧ����
	List<CategoryBean> selectBycname(String booksname) throws Exception;

	// ���ͼ�������Ϣ
	void addCate(CategoryBean catebean) throws Exception;

	// �޸�ͼ�������Ϣ
	void editCate(CategoryBean cate) throws Exception;

	// ɾ��ͼ�������Ϣ
	void delInfo(int cID) throws Exception;
}
