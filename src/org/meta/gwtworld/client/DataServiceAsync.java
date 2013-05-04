package org.meta.gwtworld.client;

import org.meta.gwtworld.client.model.ListAndDefault;
import org.meta.gwtworld.client.model.TbIdPerson;
import org.meta.gwtworld.client.model.TbWkWork;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DataServiceAsync {
	void getTbIdPerson(AsyncCallback<ListAndDefault<TbIdPerson>> callback);
	void getTbWkWork(AsyncCallback<ListAndDefault<TbWkWork>> callback);
}
