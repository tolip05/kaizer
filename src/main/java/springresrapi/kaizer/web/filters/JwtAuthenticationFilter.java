package springresrapi.kaizer.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springresrapi.kaizer.domein.entities.User;
import springresrapi.kaizer.domein.models.binding.UserLoadingBindingModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserLoadingBindingModel loadingBindingModel = new ObjectMapper()
                    .readValue(request.getInputStream(),UserLoadingBindingModel.class);
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loadingBindingModel.getUsername(),
                            loadingBindingModel.getPassword(),
                            new ArrayList<>())
            );

        }catch (IOException ignore){
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            String token = Jwts.builder()
                    .setSubject(((User)authResult.getPrincipal()).getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 1200000))
                    .signWith(SignatureAlgorithm.ES256,"Secret".getBytes())
                    .compact();
            response.addHeader("Authorisation","Bearer " + token);
    }
}
