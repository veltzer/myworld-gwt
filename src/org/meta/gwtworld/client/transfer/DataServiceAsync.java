package org.meta.gwtworld.client.transfer;

import org.meta.gwtworld.client.db.TbDevice;
import org.meta.gwtworld.client.db.TbIdPerson;
import org.meta.gwtworld.client.db.TbLocation;
import org.meta.gwtworld.client.db.TbWkWork;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DataServiceAsync {
	void getTbIdPerson(AsyncCallback<ListAndDefault<TbIdPerson>> callback);
	void getTbWkWork(AsyncCallback<ListAndDefault<TbWkWork>> callback);
	void getTbDevice(AsyncCallback<ListAndDefault<TbDevice>> callback);
	void getTbLocation(AsyncCallback<ListAndDefault<TbLocation>> callback);
}
