package it.unipr.sbrix.esercizio2.Modelli;

import java.util.EventListener;

public interface ModelListener extends EventListener {
	public void updateEventOccurred(ModelEvent evt);

}
