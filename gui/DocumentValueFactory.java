package gui;

import org.apache.lucene.document.Document;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DocumentValueFactory<Document, String> implements Callback<CellDataFeatures<Document, String>, ObservableValue<String>>{

	private java.lang.String feature;
	
	public DocumentValueFactory(java.lang.String feature) {
		this.feature = feature;
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<Document, String> doc) {
		org.apache.lucene.document.Document d = (org.apache.lucene.document.Document)doc.getValue();
		java.lang.String s = d.get(feature);
		ObservableValue<String> wrapper = (ObservableValue<String>) new ReadOnlyStringWrapper(d.get(feature));
		return wrapper;
	}
	
}
