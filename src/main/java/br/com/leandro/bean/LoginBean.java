package br.com.leandro.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	private String nomeUsuario;
	private String senha;
	
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(nomeUsuario, senha);
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		try { 
			subject.login(token);	
			this.usuario.setNome(nomeUsuario);
			this.usuario.setDataLogin(new Date());
			return "/ConsultaLancamentos?faces-redirect=true";
		} catch (UnknownAccountException ex) {
			FacesMessage mensagem = new FacesMessage("A conta informada não existe!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, mensagem);
        } catch (IncorrectCredentialsException ex) {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválido!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, mensagem);
        } catch (LockedAccountException ex) {
			FacesMessage mensagem = new FacesMessage("A conta informada está congelada!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, mensagem);
        } catch (AuthenticationException ex) {
			FacesMessage mensagem = new FacesMessage("Houve algum problema com o login!\nFavor, tente mais tarde");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, mensagem);        	
        } finally {
            token.clear();
        }
		return null;
	}

	public String logout() {
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		SecurityUtils.getSubject().logout();
		return "/Login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
