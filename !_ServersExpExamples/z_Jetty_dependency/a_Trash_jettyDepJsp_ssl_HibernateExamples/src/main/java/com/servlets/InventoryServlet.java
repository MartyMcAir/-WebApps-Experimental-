package com.servlets;

//import cc.cloudten.supercar.models.Car;
import com.google.gson.Gson;
import com.model.Car;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author james
 *
 */
public class InventoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888011897341720634L;
//	private static Log log = LogFactory.getLog(InventoryServlet.class);
	

	/**
	 * 
	 */
	public InventoryServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUri = request.getRequestURI();
//		log.info("Inventory Service Recieved Request URI: " + reqUri);

		String carId = request.getParameter("carId");

		Gson gson = new Gson();

		if (carId == null) {
			// Get all cars

			Collection<Car> cars = Car.getCars();
			
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(gson.toJson(cars));
		} else {
			// Get one car
			Car car = Car.getCar(carId);

			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(gson.toJson(car));
		}
	}
}