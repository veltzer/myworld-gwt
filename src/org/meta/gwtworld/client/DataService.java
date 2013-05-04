package org.meta.gwtworld.client;

import org.meta.gwtworld.client.db.TbDevice;
import org.meta.gwtworld.client.db.TbIdPerson;
import org.meta.gwtworld.client.db.TbLocation;
import org.meta.gwtworld.client.db.TbWkWork;
import org.meta.gwtworld.client.model.ListAndDefault;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("DataService")
public interface DataService extends RemoteService {
	ListAndDefault<TbIdPerson> getTbIdPerson();
	ListAndDefault<TbWkWork> getTbWkWork();
	ListAndDefault<TbDevice> getTbDevice();
	ListAndDefault<TbLocation> getTbLocation();
}
