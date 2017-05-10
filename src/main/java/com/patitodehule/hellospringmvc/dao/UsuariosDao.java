package com.patitodehule.hellospringmvc.dao;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patitodehule.hellospringmvc.model.Usuario;

@Repository("usuariosDao")
public class UsuariosDao extends GenericDao<Usuario>{

	public UsuariosDao() {
		super(Usuario.class);
	}
	
	@Override
	@Transactional
	public boolean logicDelete(Usuario entity) {
		
		/*Query que = sessionFactory.getCurrentSession().createQuery("update USUARIO set ESACTIVO = 0 where IDUSUARIO = :id");
        que.setParameter("id", u.getIdusuario());
        return que.executeUpdate();*/
		
		/*or*/
		
		entity.setActivo(false);
		getSessionFactory().getCurrentSession().update(entity);
		
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllActivos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarPorNombre(String nombre) {
		return (Usuario) getSessionFactory().getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.like("nombre", nombre)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuario> buscarRolesDeUsuario() {
		return getSessionFactory().getCurrentSession().createCriteria(Usuario.class)
				.setFetchMode("usuarioRols", FetchMode.JOIN) //join de la tabla USUARIO_ROL, ver query en consola
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY) //se especifica porque  USUARIO_ROL contiene a USUARIO
				.list();
	}

}
