package MotorEntix.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import MotorEntix.model.Usuario;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            Usuario u = customUserDetails.getUsuario();

            session.setAttribute("usuarioId", u.getId_usuario());
            session.setAttribute("usuarioEmail", u.getCorreo());
            session.setAttribute("usuarioLogueado", u.getNombre());
            session.setAttribute("rol", u.getRol());
            session.setAttribute("usuario", u);

            String rol = u.getRol() != null ? u.getRol().toLowerCase() : "";
            switch (rol) {
                case "administrador" -> response.sendRedirect(request.getContextPath() + "/admin/panel");
                case "cliente" -> response.sendRedirect(request.getContextPath() + "/panel.cliente");
                case "trabajador" -> response.sendRedirect(request.getContextPath() + "/panel.trabajador");
                case "dueno" -> response.sendRedirect(request.getContextPath() + "/panel.dueno");
                default -> response.sendRedirect(request.getContextPath() + "/login?error=rol");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
