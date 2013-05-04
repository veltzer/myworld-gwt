package org.meta.gwtworld.client.model;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

public class ComboAsync<T> implements AsyncCallback<ListAndDefault<T>> {
	private ComboBox<T> combo;
	public ComboAsync(ComboBox<T> combo) {
		this.combo=combo;
	}
	public void onSuccess(ListAndDefault<T> result) {
		combo.getStore().addAll(result.list);
		combo.setValue(result.def);
		combo.setEnabled(true);
	}
	public void onFailure(Throwable caught) {
		Info.display("error", caught.toString());
	}
}