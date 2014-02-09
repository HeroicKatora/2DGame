package control;

public class FFControlEvent extends FFEvent{
    /**
     * 
     */
    private static final long serialVersionUID = "FFControlEvent".hashCode();

    @Override
    public Class<? extends FFListener> getListenerClass() {
	// TODO Auto-generated method stub
	return FFControlListener.class;
    }
}
