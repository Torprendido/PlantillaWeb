package com.patitodehule.hellospringmvc.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patitodehule.hellospringmvc.dao.UsuariosDao;
import com.patitodehule.hellospringmvc.model.Usuario;

@Controller
@RequestMapping(value="/administracionUsuarios")
public class AdministracionUsuarios {
	
	@Autowired
	private UsuariosDao usuariosDao;
	
	@RequestMapping(value="/ventanaUsuarios.do")
	public String ventanaUsuario(Model model) {
		List<Usuario> usuarios = usuariosDao.buscarRolesDeUsuario();
		model.addAttribute("usuarios", usuarios);
		return "/administracionUsuarios/ventanaUsuarios";
	}
	
	@RequestMapping(value="/crearUsuario.do", method=RequestMethod.POST)
	public @ResponseBody Usuario crearUsuario(/*desde el form*/  @ModelAttribute Usuario usuario) {
		try {
			usuariosDao.save(usuario); //El id es autoincrement
		} catch(Exception ex) {
			usuario = null;
		}
		return usuario; //regresa objeto con nuevo id independientemente del parametro del form
	}
	
	@RequestMapping(value="/actualizarUsuario.do", method=RequestMethod.POST)
	public @ResponseBody Usuario actualizarUsuario(/*desde el form*/  @ModelAttribute Usuario usuario) {
		try {
			usuariosDao.update(usuario);
		} catch(Exception ex) {
			//No actualiza PRIMARY KEY
			usuario = null;
		}
		return usuario;
	}
	
}
