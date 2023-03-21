package board.boardproject.confing.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        System.out.println(allPrincipals);
        if (allPrincipals.contains(authentication.getPrincipal())){
            response.sendRedirect("/chat/room");
        }
        setDefaultTargetUrl("/board/list");
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
