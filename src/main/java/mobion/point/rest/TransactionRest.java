package mobion.point.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mobion.point.bean.BaseBean;
import mobion.point.bean.TransactionsGroup;
import mobion.point.bean.TransferParam;
import mobion.point.exception.MobionException;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.spi.validation.ValidateRequest;

@Path("/transaction")
@Produces({MediaType.APPLICATION_JSON})
@ValidateRequest
public interface TransactionRest {
	@POST
	@Path("/add")
	public BaseBean addPoint(@NotNull(message="id canot be null") @FormParam("id") String memId, @NotNull(message="point canot be null") @FormParam("point") Long point) throws MobionException;

	@POST
	@Path("/subtract")
	public BaseBean subtractPoint(@NotNull @FormParam("id") String memId, @NotNull @FormParam("point") Long point) throws MobionException;

	@POST
	@Path("/transfer")
	public BaseBean transferPoint(@Form @Valid TransferParam transferParam) throws MobionException;
	
	@POST
	@Path("/transfer_wait")
	public TransactionsGroup transferPointAndWait(@Form @Valid TransferParam transferParam) throws MobionException;
	
	@POST
	@Path("/confirm_transfer_wait")
	public BaseBean confirmTransferPointAndWait(@NotNull @FormParam("source_id") String sourceMemId, @NotNull @FormParam("trans_group_id") Long transGroupId) throws MobionException;

	@POST
	@Path("/cancel_transfer_wait")
	public BaseBean cancelTransferAndWait(@NotNull @FormParam("source_id") String sourceMemId, @NotNull @FormParam("trans_group_id") Long transGroupId) throws MobionException;
}
