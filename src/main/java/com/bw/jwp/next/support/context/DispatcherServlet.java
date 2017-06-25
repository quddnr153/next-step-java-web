package com.bw.jwp.next.support.context;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;

/**
 * @author Byungwook, Lee
 */
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DispatcherServlet.class);
	private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

	private RequestMapping rm;

	@Override
	public void init() throws ServletException {
		rm = new RequestMapping();
		rm.initMapping();
	}

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		final String requestUri = req.getRequestURI();
		LOG.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

		final Controller controller = rm.findController(requestUri);

		try {
			final String viewName = controller.execute(req, res);
			move(viewName, req, res);
		} catch (Throwable e) {
			LOG.error("Exception : {}", e);
			throw new ServletException(e.getMessage());
		}
	}

	private void move(final String viewName, final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			res.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}

		final RequestDispatcher rd = req.getRequestDispatcher(viewName);

		rd.forward(req, res);
	}
}
