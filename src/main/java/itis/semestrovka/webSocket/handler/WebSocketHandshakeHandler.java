package itis.semestrovka.webSocket.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import itis.semestrovka.models.User;
import itis.semestrovka.security.details.UserDetailsImpl;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.WebUtils;
import org.springframework.web.socket.WebSocketHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Map;

@Component
public class WebSocketHandshakeHandler implements HandshakeHandler {

    private final DefaultHandshakeHandler handshakeHandler = new DefaultHandshakeHandler();

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        Cookie[] cookies = request.getCookies();
        String token;
        DecodedJWT jwt;
        UserDetailsImpl userDetails;
        if (cookies != null) {
            token = WebUtils.getCookie(request, "token").getValue();
            jwt = JWT.decode(token);
            userDetails = new UserDetailsImpl(User.builder()
                    .id(Long.parseLong(jwt.getSubject()))
                    .email(jwt.getClaim("email").asString())
                    .username(jwt.getClaim("username").asString())
                    .role(User.Role.valueOf(jwt.getClaim("role").asString()))
                    .state(User.State.valueOf(jwt.getClaim("state").asString()))
                    .birth(Date.valueOf(jwt.getClaim("birth").asString()))
                    .photo(jwt.getClaim("photo").asString())
                    .phone(jwt.getClaim("phone").asString())
                    .build());
            map.put("user", userDetails);
        }

        return handshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
    }
}