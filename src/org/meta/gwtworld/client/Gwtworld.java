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
import org.meta.gwtworld.client.transfer.DataService;
import org.meta.gwtworld.client.transfer.DataServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.TimeField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

public class Gwtworld implements EntryPoint {

	public void onModuleLoad() {
		/*
		final ContentPanel center=new ContentPanel();
		final BorderLayoutData centerData=new BorderLayoutData();
		centerData.setCollapsible(false);
		centerData.setCollapseMini(false);
		center.add(createTab());
		final BorderLayoutContainer con=new BorderLayoutContainer();
		con.setCenterWidget(center,centerData);
		con.setBorders(false);
		RootPanel.get().add(con);
		*/
		/* only tab version */
		RootPanel.get().add(createTab());
		RootPanel.get().setTitle("MyWorld");
		RootPanel.get().getElement().getStyle().setBorderWidth(0, Unit.EM);
		RootPanel.get().getElement().getStyle().setMargin(0, Unit.EM);
	}

	private Widget createTab() {
		final TabPanel tp=new TabPanel();
		tp.add(createForm(),"Work View");
		return tp;
	}

	private Widget createForm() {
		final DataServiceAsync ds=GWT.create(DataService.class);
		
		VerticalLayoutContainer vlc=new VerticalLayoutContainer();
		
		TbIdPersonProperties person_props = GWT.create(TbIdPersonProperties.class);
		final ComboBox<TbIdPerson> personCombo=MyCombo.createCombo(person_props.key(), person_props.label(), ds);
	    ds.getTbIdPerson(new ComboAsync<TbIdPerson>(personCombo));
		vlc.add(new FieldLabel(personCombo, "Person"), new VerticalLayoutData(1, -1));

		TbWkWorkProperties work_props = GWT.create(TbWkWorkProperties.class);
		final ComboBox<TbWkWork> workCombo=MyCombo.createCombo(work_props.key(), work_props.label(), ds);
	    ds.getTbWkWork(new ComboAsync<TbWkWork>(workCombo));
		workCombo.setEmptyText("Put the name of the work here...");
		vlc.add(new FieldLabel(workCombo, "Work"), new VerticalLayoutData(1, -1));

		TbDeviceProperties device_props = GWT.create(TbDeviceProperties.class);
		final ComboBox<TbDevice> deviceCombo=MyCombo.createCombo(device_props.key(), device_props.label(), ds);
	    ds.getTbDevice(new ComboAsync<TbDevice>(deviceCombo));
		vlc.add(new FieldLabel(deviceCombo, "Device"), new VerticalLayoutData(1, -1));
		
		TbLocationProperties loc_props = GWT.create(TbLocationProperties.class);
		final ComboBox<TbLocation> locCombo=MyCombo.createCombo(loc_props.key(), loc_props.label(), ds);
	    ds.getTbLocation(new ComboAsync<TbLocation>(locCombo));
		vlc.add(new FieldLabel(locCombo, "Location"), new VerticalLayoutData(1, -1));
		
		final DateField df=new DateField();
		df.setValue(new Date());
		df.setAllowBlank(false);
		df.setAutoValidate(true);
		df.setValidationDelay(0);
		vlc.add(new FieldLabel(df,"Date"), new VerticalLayoutData(1, -1));
		
		final TimeField tf=new TimeField();
		tf.setValue(new Date());
		tf.setAllowBlank(false);
		tf.setAutoValidate(true);
		tf.setValidationDelay(0);
		vlc.add(new FieldLabel(tf,"Time"), new VerticalLayoutData(1, -1));
		
		final HorizontalLayoutContainer hp=new HorizontalLayoutContainer();
		final CheckBox ar=new CheckBox();
		final TextField rf=new TextField();
		rf.setValue("");
		rf.setEmptyText("Put your comment here...");
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

		vlc.add(new FieldLabel(hp,"Remark"), new VerticalLayoutData(1, -1));
		
		final TextButton save=new TextButton("Save");
		final Runnable r=new Runnable() {
			@Override
			public void run() {
				save.setEnabled(
						personCombo.isCurrentValid() &&
						workCombo.isCurrentValid() &&
						deviceCombo.isCurrentValid() &&
						locCombo.isCurrentValid() &&
						df.isCurrentValid() &&
						tf.isCurrentValid() &&
						((ar.getValue() && rf.getCurrentValue()!="") || (!ar.getValue()))
				);
			}
		};
		r.run();
		addValidate(personCombo,r);
		addValidate(workCombo,r);
		addValidate(deviceCombo,r);
		addValidate(locCombo,r);
		addValidate(df,r);
		addValidate(tf,r);
		addValidate(rf,r);
		ar.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				r.run();
			}
		});

		final FormPanel form=new FormPanel();
		form.add(vlc);

		FramedPanel panel=new FramedPanel();
		panel.setHeadingText("Existing work viewed");
		panel.add(form);
		panel.add(save);

		return panel;
	}

	private <T> void addValidate(ValueBaseField<T> df, final Runnable r) {
		//df.setAllowBlank(false);
		df.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				r.run();
			}
		});
		df.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				r.run();
			}
		});
		df.addValueChangeHandler(new ValueChangeHandler<T>() {
			@Override
			public void onValueChange(ValueChangeEvent<T> event) {
				r.run();
			}
		});
		df.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				r.run();
			}
		});
	}
}
