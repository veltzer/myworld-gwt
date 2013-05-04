package org.meta.gwtworld.client;

import org.meta.gwtworld.client.model.ListAndDefault;
import org.meta.gwtworld.client.model.TbIdPerson;
import org.meta.gwtworld.client.model.TbWkWork;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("DataService")
public interface DataService extends RemoteService {
	ListAndDefault<TbIdPerson> getTbIdPerson();
	ListAndDefault<TbWkWork> getTbWkWork();
}
