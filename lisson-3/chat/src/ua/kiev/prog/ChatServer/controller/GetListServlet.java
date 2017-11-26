package ua.kiev.prog.ChatServer.controller;

import ua.kiev.prog.ChatServer.repository.MessageList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();

	private int from = 0;

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("messageFrom");
		String userFor = req.getParameter("to");
		String userFrom = req.getParameter("from");

		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}
		
		String json = msgList.toJSON(userFor, userFrom, from);
		resp.setHeader("n", String.valueOf(MessageList.getInstance().getN()));
		System.out.println(json);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
			byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);
		}
	}
}
