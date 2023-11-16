 /*
       A trivial applet that tests the StopWatchTimer component.
       The applet just creates and shows a StopWatchTimer.
    */
    
    import java.awt.*;
    
    public class TestStopWatchRunner extends Frame {
    
       /**
		 * 
		 */
		private static final long serialVersionUID = -8285734181986732753L;

	public void init() {
          
          TestStopWatchRunner watch = new TestStopWatchRunner();
          watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
          watch.setBackground(Color.white);
          watch.setForeground( new Color(180,0,0) );
          setBackground(Color.white);
          setLayout(new BorderLayout() );
          add(watch, BorderLayout.CENTER);
          
       }
    
    }