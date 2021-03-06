
package JavaTimer;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TimerGUI extends JFrame implements KeyListener, Runnable{
    
    int hour = 0,
        min = 0,
        sec = 0;
    
    JLabel timer = new JLabel();
    JLabel state = new JLabel();
    JLabel guides = new JLabel();
    
    
    
    String timerString = hour + ":" + min +":" + sec;
    
    
    private boolean isPlayClicked = true,
            isPauseClicked,
            isStopClicked;
    
    
    
    TimerGUI(){
        
        init();
 
    }

    
    
    private void init() {
        
        setVisible(true);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(25,25,30));
        addKeyListener(this);
        setTitle("Java Timer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        timer.setBounds(130, 67, 350, 100);
        timer.setForeground(new Color(200,200,200));
        timer.setFont(new Font("Arial",1,80));
        add(timer);
        
        state.setBounds(270, 20, 70, 30);
        state.setForeground(new Color(200,160,160));
        state.setFont(new Font("Arial",1,10));
        add(state);
        
        guides.setBounds(110, 175, 470, 80);
        guides.setForeground(new Color(200,200,200));
        guides.setFont(new Font("Arial",1,12));
        guides.setText("press SPACE to pause, resume or start. And press ENTER to stop");
        add(guides);
        
        //run();
    }

    
    
    
    private void runTimer() {
         
        checkTime();
        setTimerString();
        timer.setText(timerString);
        sec++;
 
    }

    private void setTimerString() {
        timerString = String.format("%02d", hour) + ":" + String.format("%02d", min) +":" + String.format("%02d", sec);
    }
     
    
    
    private void pauseTimer() {
         
        setTimerString();
        timer.setText(timerString);
        state.setText("Paused");
        
 
    }
    
    private void stopTimer() {
         
        hour = 0;
        min = 0;
        sec = 0;
        
        setTimerString();
        timer.setText(timerString);
        state.setText("Stopped");
        
    }
     
    
    
    
    
    private void checkTime(){
        if(sec > 59){
            min++;
            sec = 0;
        }
        if(min > 59){
            hour++;
            min = 0;
        }
    }
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            isStopClicked = true;
            isPauseClicked = false;
            isPlayClicked = false;
        }
        if(ke.getKeyCode()==KeyEvent.VK_SPACE){
            if(isPauseClicked==false && isStopClicked!=true){
                isPauseClicked = true;
                isStopClicked = false;
                isPlayClicked = false;
            }
            else if(isStopClicked){
                isPauseClicked = false;
                isStopClicked = false;
                isPlayClicked = true;
                state.setText("");
            }
            else{
                isPauseClicked = false;
                isStopClicked = false;
                isPlayClicked = true;
                System.out.println("Resumed Again After Pause ..");
                state.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    
    
    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("inside run();");
                if (isPlayClicked) {
                    System.out.println("inside while();");
                    System.out.println(timer.getText());
                    System.out.println("Threads: " + Thread.activeCount());
                    runTimer();
                }

                if (isPauseClicked) {
                    isPlayClicked = false;
                    isStopClicked = false;
                    pauseTimer();
                }
                if (isStopClicked) {
                    isPlayClicked = false;
                    isPauseClicked = false;
                    stopTimer();
                }

                Thread.sleep(900);
                // a second is set to 900ms duo to the delay in painting the 
                // graphics(JLabel) into the window(JFrame)

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    

    
    
}
