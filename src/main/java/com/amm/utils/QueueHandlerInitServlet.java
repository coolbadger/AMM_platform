package com.amm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.amm.service.RecMesService;
import com.amm.service.impl.RecMesServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author zhangjunxian
 * 
 */
public class QueueHandlerInitServlet extends HttpServlet {

	public QueueHandlerInitServlet() {
		super();
	}

	public void init() throws ServletException {
		try {
			System.out.println("####################  UDPInitServlet  init");

			ServletContext sc = this.getServletContext();
			WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			//IbusTpviService ibusTpviService = (IbusTpviServiceImpl) ac.getBean("ibusTpviService");
			RecMesService recMesService = (RecMesServiceImpl) ac.getBean("RecMesService");
			//启动监听线程
			QueueHandlerThread queueHandlerThread = new QueueHandlerThread(recMesService);
			Thread thread = new Thread(queueHandlerThread);
			thread.start();
		}catch (Exception e){
			e.printStackTrace();

		}


	}
}
