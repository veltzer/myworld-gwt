package org.meta.gwtworld.client.db;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TbDeviceProperties extends PropertyAccess<TbDevice> {
	@Path("id")
	ModelKeyProvider<TbDevice> key();
	
	@Path("name")
	LabelProvider<TbDevice> label();
	
}