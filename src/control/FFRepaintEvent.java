package control;

public class FFRepaintEvent extends FFEvent {
    @Override
    public Class<? extends FFListener> getListenerClass(){
	return FFRepaintListener.class;
    }
}
