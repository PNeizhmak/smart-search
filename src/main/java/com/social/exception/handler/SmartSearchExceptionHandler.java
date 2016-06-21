package com.social.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.social.exception.SmartSearchException;

@Provider
@Singleton
public class SmartSearchExceptionHandler implements ExceptionMapper<SmartSearchException> {

    @Inject
    private Gson gson;

    @Override
    public Response toResponse(SmartSearchException e) {
        return Response.ok(gson.toJson(e)).build();
    }

}
