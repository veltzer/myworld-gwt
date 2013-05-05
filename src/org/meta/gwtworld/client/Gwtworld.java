package org.meta.gwtworld.client;

import java.util.Date;

import org.meta.gwtworld.client.db.TbDevice;
import org.meta.gwtworld.client.db.TbDeviceProperties;
import org.meta.gwtworld.client.db.TbIdPerson;
import org.meta.gwtworld.client.db.TbIdPersonProperties;
import org.meta.gwtworld.client.db.TbLocation;
import org.meta.gwtworld.client.db.TbLocationProperties;
import org.meta.gwtworld.client.db.TbWkWork;
import org.meta.gwtworld.client.db.TbWkWorkProperties;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
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
		tp.add(createForm(),"Work View");
		//tp.setWidth("400px");
		return tp;
	}

	private Widget createForm() {
		final DataServiceAsync ds=GWT.create(DataService.class);
		
		FramedPanel panel=new FramedPanel();
		panel.setHeadingText("existing work viewed");
		//panel.setWidth("100%");
		//panel.setWidth("400px");
		
		final FormPanel form=new FormPanel();
		final TextButton save=new TextButton("Save");
		
		panel.add(form);
		
		VerticalLayoutContainer p=new VerticalLayoutContainer();
		form.add(p);
		
		TbIdPersonProperties person_props = GWT.create(TbIdPersonProperties.class);
		final ComboBox<TbIdPerson> personCombo=MyCombo.createCombo(person_props.key(), person_props.label(), ds);
	    ds.getTbIdPerson(new ComboAsync<TbIdPerson>(personCombo));
		p.add(new FieldLabel(personCombo, "Person"), new VerticalLayoutData(1, -1));

		TbWkWorkProperties work_props = GWT.create(TbWkWorkProperties.class);
		final ComboBox<TbWkWork> workCombo=MyCombo.createCombo(work_props.key(), work_props.label(), ds);
	    ds.getTbWkWork(new ComboAsync<TbWkWork>(workCombo));
		p.add(new FieldLabel(workCombo, "Work"), new VerticalLayoutData(1, -1));
		workCombo.setEmptyText("Put the name of the work here...");
		/*
		workCombo.addValueChangeHandler(new ValueChangeHandler<TbWkWork>() {
			@Override
			public void onValueChange(ValueChangeEvent<TbWkWork> event) {
				save.setEnabled(form.isValid());
			}
		});
		*/

		TbDeviceProperties device_props = GWT.create(TbDeviceProperties.class);
		final ComboBox<TbDevice> deviceCombo=MyCombo.createCombo(device_props.key(), device_props.label(), ds);
	    ds.getTbDevice(new ComboAsync<TbDevice>(deviceCombo));
		p.add(new FieldLabel(deviceCombo, "Device"), new VerticalLayoutData(1, -1));
		
		TbLocationProperties loc_props = GWT.create(TbLocationProperties.class);
		final ComboBox<TbLocation> locCombo=MyCombo.createCombo(loc_props.key(), loc_props.label(), ds);
	    ds.getTbLocation(new ComboAsync<TbLocation>(locCombo));
		p.add(new FieldLabel(locCombo, "Location"), new VerticalLayoutData(1, -1));
		
		final DateField df=new DateField();
		df.setValue(new Date());
		p.add(new FieldLabel(df,"Date"), new VerticalLayoutData(1, -1));
		
		final TimeField tf=new TimeField();
		tf.setValue(new Date());
		p.add(new FieldLabel(tf,"Time"), new VerticalLayoutData(1, -1));
		
		final HorizontalLayoutContainer hp=new HorizontalLayoutContainer();
		final CheckBox ar=new CheckBox();
		final TextField rf=new TextField();
		ar.setValue(false);
		//p.add(new FieldLabel(ar,"Attach remark"), new VerticalLayoutData(1, -1));
		//p.add(new FieldLabel(ar,"Attach remark"));
		ar.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				rf.setEnabled(event.getValue());
			}
		});
		rf.setEnabled(false);
		
		hp.add(ar);
		hp.add(rf, new HorizontalLayoutData(1,1));

		p.add(new FieldLabel(hp,"Remark"), new VerticalLayoutData(1, -1));
		
		panel.addButton(save);
	    //panel.addButton(new TextButton("Cancel"));
		
		return panel;
	}
}
