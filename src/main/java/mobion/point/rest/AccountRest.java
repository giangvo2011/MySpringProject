package mobion.point.rest;

import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mobion.point.bean.BaseBean;
import mobion.point.bean.MemberInfo;
import mobion.point.exception.MobionException;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.spi.validation.ValidateRequest;

@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
@ValidateRequest
public interface AccountRest {
	@POST
	@Path("/create")
	public MemberInfo createAccount(@Form @Valid MemberInfo memberInfo) throws MobionException;

	@GET
	@Path("/{id}")
	public MemberInfo getAccount(@PathParam("id") String id) throws MobionException;

	@POST
	@Path("/update")
	public MemberInfo updateAccount(@Form @Valid MemberInfo data) throws MobionException;
	
	@POST
	@Path("/delete")
	public BaseBean deleteAccount(@FormParam("id") String id) throws MobionException;
}
