package com;

import java.io.IOException;

import javax.servlet.http.HttpServlet;

import com.google.inject.servlet.GuiceFilter;
import com.config.Config;
import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;

public class GrizzlyWS {
    public static void main(String[] args) {
        try {
            GrizzlyWebServer server = new GrizzlyWebServer(8081);
            ServletAdapter adapter = new ServletAdapter(new DummySevlet());
            adapter.addServletListener(Config.class.getName());
            adapter.addFilter(new GuiceFilter(), "GuiceFilter", null);
            server.addGrizzlyAdapter(adapter, new String[]{ "/" });
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@SuppressWarnings("serial")
class DummySevlet extends HttpServlet { }