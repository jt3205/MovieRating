package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String viewPath = "/WEB-INF/views/"; // ���θ� �����صΰ�.

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	private void dispatcher(HttpServletRequest request, HttpServletResponse response, String view) {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath + view);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("�ش� ��� �������� �ʽ��ϴ�."); // �� ���� ������.
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		// 2. Ŭ���̾�Ʈ�� ��û path�� ���� ������ �б�ó�� �Ѵ�.
		if (path.equals("/login.do")) {

		}
	}
}