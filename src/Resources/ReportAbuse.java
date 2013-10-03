package Resources;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.google.appengine.api.images.Composite;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

public abstract class ReportAbuse extends Composite {

	Button but = new Button("Report abuse");
	
		public ReportAbuse()
			{
				DialogBox db = new DialogBox();
				db.setText("Please report abuse");
				HorizontalPanel panel = new HorizontalPanel();
				panel.setSpacing(12);
				panel.add(but);
				TextArea ta = new TextArea();
				panel.add(ta);
				db.setVisible(true);

			}

		void onClick()
			{
				Window.alert("Thank you for reporting abuse");
			
			}
	
}
