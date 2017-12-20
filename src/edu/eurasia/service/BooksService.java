package edu.eurasia.service;

import java.util.List;

import edu.eurasia.entity.BooksBean;

public interface BooksService {
	
	// ������ѯBooks�������ֶ��Լ�Category���е�cname�ֶ�
	List<BooksBean> selectBooksAll() throws Exception;

	// ��bnameģ����ѯBooks�������ֶ��Լ�Category���е�cname�ֶ�
	List<BooksBean> selectBycname(String booksname) throws Exception;

	// ���ͼ����Ϣ
	void addBooks(BooksBean books) throws Exception;

	// �޸�ͼ����Ϣ
	void editBooks(BooksBean booksbean) throws Exception;

	// ɾ��ͼ����Ϣ
	void delInfo(int bID) throws Exception;

}
