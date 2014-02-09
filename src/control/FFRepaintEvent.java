package control;

public class FFRepaintEvent extends FFEvent {
    /**
     * 
     */
    private static final long serialVersionUID = "FFRepaintEvent".hashCode();

    @Override
    public Class<? extends FFListener> getListenerClass(){
	return FFRepaintListener.class;
    }
}
