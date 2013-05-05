package org.meta.gwtworld.client;

import org.meta.gwtworld.client.transfer.ListAndDefault;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

public class ComboAsync<T> implements AsyncCallback<ListAndDefault<T>> {
	private ComboBox<T> combo;
	public ComboAsync(ComboBox<T> combo) {
		this.combo=combo;
	}
	public void onSuccess(ListAndDefault<T> result) {
		if(result.list!=null) {
			combo.getStore().addAll(result.list);
		}
		if(result.def!=null) {
			combo.setValue(result.def);
		}
		combo.setEnabled(true);
	}
	public void onFailure(Throwable caught) {
		Info.display("error", caught.toString());
	}
}