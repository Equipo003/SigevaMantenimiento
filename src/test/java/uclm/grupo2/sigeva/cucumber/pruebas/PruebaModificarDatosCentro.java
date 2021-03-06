package uclm.grupo2.sigeva.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import uclm.grupo2.sigeva.dao.UsuarioDAO;
import uclm.grupo2.sigeva.http.CentroSaludController;
import uclm.grupo2.sigeva.http.LoginController;
import uclm.grupo2.sigeva.model.CentroSalud;
import uclm.grupo2.sigeva.model.CentroSaludDTO;
import uclm.grupo2.sigeva.model.Usuario;
import uclm.grupo2.sigeva.model.UsuarioDTO;
import io.cucumber.java.en.Then;
public class PruebaModificarDatosCentro {
	

	@Autowired
	private CentroSaludController CentroCtrl;
	
	@Autowired
	private UsuarioDAO user;
	
	@Autowired
	private LoginController LoginCtrl;
	
	@Given("los datos asociados {string}, {string}, {string}, {string}, {string}, {string} y {string}")
	public void los_datos_asociados_y(String nombreCentro, String direccionCentro, String numeroVacunas, String fInicio, String fFin, String franja, String cupo) {
		UsuarioDTO uDTO= new UsuarioDTO();
		
		Optional<Usuario> optUser = user.findByLogin("administrador");
		uDTO.setId(optUser.get().getId());
		uDTO.setLogin(optUser.get().getLogin());
		uDTO.setPassword(optUser.get().getPassword());
		uDTO.setNombre(optUser.get().getNombre());
		uDTO.setApellidos(optUser.get().getApellidos());
		uDTO.setTelefono(optUser.get().getTelefono());
		uDTO.setDni(optUser.get().getDni());
		uDTO.setRol(optUser.get().getRol());
		uDTO.setCs(optUser.get().getCs());
		uDTO.setDosis(optUser.get().getDosis());
		
		LoginCtrl.iniciarSesion(uDTO);
		
		nombreCentro= "Miguelturra33"; direccionCentro="Avenida Parque 8"; numeroVacunas="7780"; fInicio = "09:30"; fFin = "14:00"; franja= "6"; cupo="5";
		String nombreCentroN= "Miguelturra34"; String direccionCentroN="Avenida Parque 6"; String numeroVacunasN="8780";
		
		CentroSalud centro = new CentroSalud();
		centro.setNombre(nombreCentro);
		centro.setDireccion(direccionCentro);
		centro.setNumVacunas(numeroVacunas);
		centro.setfInicio(fInicio);
		centro.setfFin(fFin);
		centro.setFranja(franja);
		centro.setCupo(cupo);
		
		CentroSaludDTO csDTO= new CentroSaludDTO();
		csDTO.setId(centro.getId());
		csDTO.setNombre(centro.getNombre());
		csDTO.setDireccion(centro.getDireccion());
		csDTO.setNumVacunas(centro.getNumVacunas());
		csDTO.setfInicio(centro.getfInicio());
		csDTO.setfFin(centro.getfFin());
		csDTO.setFranja(centro.getFranja());
		csDTO.setCupo(centro.getCupo());
		CentroCtrl.insertarCentro(csDTO);
		
		centro.setNombre(nombreCentroN);
		centro.setDireccion(direccionCentroN);
		centro.setNumVacunas(numeroVacunasN);
		csDTO.setNombre(centro.getNombre());
		csDTO.setDireccion(centro.getDireccion());
		csDTO.setNumVacunas(centro.getNumVacunas());
		
		assertEquals("Centro modificado",CentroCtrl.modificarCentro(csDTO));
		CentroCtrl.borrarCentro(csDTO);
	}

	@Then("se ha modificado la informacion del centro correctamente")
	public void se_ha_modificado_la_informacion_del_centro_correctamente() {

	}
}
