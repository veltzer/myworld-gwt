package org.meta.gwtworld.client;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class MyCombo {
	
	static public <T,T_Props> ComboBox<T> createCombo(ModelKeyProvider<T> key, LabelProvider<T> lp,final DataServiceAsync ds) {
	    final ListStore<T> store = new ListStore<T>(key);
		final ComboBox<T> combo=new ComboBox<T>(store,lp);
		combo.setForceSelection(true);
		combo.setAllowBlank(false);
		combo.setEnabled(false);
		combo.setTypeAhead(true);
		return combo;
	}
		
}
