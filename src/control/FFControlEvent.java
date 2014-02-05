package control;

public class FFControlEvent extends FFEvent{
@Override
public Class<? extends FFListener> getListenerClass() {
	// TODO Auto-generated method stub
	return FFControlListener.class;
}
}
