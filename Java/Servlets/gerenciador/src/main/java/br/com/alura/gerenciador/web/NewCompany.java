package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Company;
import br.com.alura.gerenciador.dao.CompanyDAO;

public class NewCompany implements Task {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter("name");
        Company company = new Company(name);
        new CompanyDAO().add(company);

        req.setAttribute("name", name);
        
		return "/WEB-INF/pages/NewCompany.jsp";
	}
}
