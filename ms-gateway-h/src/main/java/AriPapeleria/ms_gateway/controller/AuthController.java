package AriPapeleria.ms_gateway.controller;

import AriPapeleria.ms_gateway.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> request){

        String username = request.get("username");
        String password = request.get("password");

        if(!"admin".equals(username) || !"1234".equals(password)){
            throw new RuntimeException("Credenciales inválidas");
        }
        String token = jwtService.generateToken(username);
        return Map.of("token", token);
    }

}
