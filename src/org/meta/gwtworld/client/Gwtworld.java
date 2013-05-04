package org.meta.gwtworld.client;

import java.util.Date;

import org.meta.gwtworld.client.model.ComboAsync;
import org.meta.gwtworld.client.model.TbIdPerson;
import org.meta.gwtworld.client.model.TbIdPersonProperties;
import org.meta.gwtworld.client.model.TbWkWork;
import org.meta.gwtworld.client.model.TbWkWorkProperties;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TimeField;

public class Gwtworld implements EntryPoint {
	public void onModuleLoad() {
		final ContentPanel center=new ContentPanel();
		final BorderLayoutData centerData=new BorderLayoutData();
		centerData.setCollapsible(false);
		centerData.setCollapseMini(false);
		center.add(createTab());
		final BorderLayoutContainer con=new BorderLayoutContainer();
		con.setCenterWidget(center,centerData);
		con.setBorders(false);
		RootPanel.get().add(con);
		/* only tab version */
		/*
		Widget tab=createTab();
		RootPanel.get().add(tab);
		*/
	}

	private Widget createTab() {
		final TabPanel tp=new TabPanel();
		tp.add(createForm(),"SawMovie");
		//tp.setWidth("400px");
		return tp;
	}

	private Widget createForm() {
		final DataServiceAsync ds=GWT.create(DataService.class);
		
		FramedPanel panel=new FramedPanel();
		panel.setHeadingText("existing movie watched form");
		//panel.setWidth("100%");
		//panel.setWidth("400px");
		
		VerticalLayoutContainer p=new VerticalLayoutContainer();
		panel.add(p);
		
		TbIdPersonProperties person_props = GWT.create(TbIdPersonProperties.class);
		final ComboBox<TbIdPerson> personCombo=MyCombo.createCombo(person_props.key(), person_props.fullNameLabel(), ds);
	    ds.getTbIdPerson(new ComboAsync<TbIdPerson>(personCombo));
		p.add(new FieldLabel(personCombo, "Person"), new VerticalLayoutData(1, -1));

		TbWkWorkProperties work_props = GWT.create(TbWkWorkProperties.class);
		final ComboBox<TbWkWork> workCombo=MyCombo.createCombo(work_props.key(), work_props.fullNameLabel(), ds);
	    ds.getTbWkWork(new ComboAsync<TbWkWork>(workCombo));
		p.add(new FieldLabel(workCombo, "Work"), new VerticalLayoutData(1, -1));
		
		final DateField df=new DateField();
		df.setValue(new Date());
		p.add(new FieldLabel(df,"Date"), new VerticalLayoutData(1, -1));
		
		final TimeField tf=new TimeField();
		tf.setValue(new Date());
		p.add(new FieldLabel(tf,"Time"), new VerticalLayoutData(1, -1));
	
		return panel;
	}
}
