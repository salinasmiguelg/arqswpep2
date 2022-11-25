package com.application.justificativoservice.controller;

import com.application.justificativoservice.dto.Mensaje;
import com.application.justificativoservice.entities.Justificativo;
import com.application.justificativoservice.security.dto.JwtDto;
import com.application.justificativoservice.security.dto.LoginUsuario;
import com.application.justificativoservice.security.dto.NuevoUsuario;
import com.application.justificativoservice.security.entity.Rol;
import com.application.justificativoservice.security.entity.Usuario;
import com.application.justificativoservice.security.enums.RolNombre;
import com.application.justificativoservice.security.jwt.JwtProvider;
import com.application.justificativoservice.security.service.RolService;
import com.application.justificativoservice.security.service.UsuarioService;
import com.application.justificativoservice.service.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/justificativo")
public class JustificativoController {
    @Autowired
    private JustificativoService justificativoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Justificativo>> getAll() {
        List<Justificativo> justificativos = justificativoService.getAll();
        if(justificativos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(justificativos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justificativo> getById(@PathVariable("id") int id) {
        Justificativo justificativo = justificativoService.getJustificativoById(id);
        if(justificativo == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(justificativo);
    }

    @GetMapping("/byempleadorut/{empleadorut}")
    public ResponseEntity<List<Justificativo>> getByRut(@PathVariable("empleadorut") String empleadorut) {
        List<Justificativo> justificativos = justificativoService.getJustificativoByRut(empleadorut);
        if(justificativos == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(justificativos);
    }

    @PostMapping()
    public ResponseEntity<Justificativo> save(@RequestBody Justificativo justificativo) {
        Justificativo justificativoNew = justificativoService.save(justificativo);
        return ResponseEntity.ok(justificativoNew);
    }

    @PostMapping("/saveJustificativos")
    public ResponseEntity<String> saveJustificativos(@RequestBody List<Justificativo> justificativos) {
        for (Justificativo justificativo: justificativos) {
            Justificativo justificativoNew = justificativoService.save(justificativo);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Justificativos bien subidos :D");
    }
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
