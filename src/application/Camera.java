package application;
//package application;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;



public class Camera {

   static {
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
   }
   public static boolean isRun = true;
   public static boolean isEnd = false;

   public static void main(String[] args) {
	  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      JFrame window = new JFrame("按<Esc>断开与相机的连接");
      window.setSize(640, 480);
      window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      window.setLocationRelativeTo(null);
      window.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            isRun = false;
            if ( isEnd ) {
               window.dispose();
               System.exit(0);
            }
            else {
               System.out.println(
                     "首先按<Esc>，然后按Close");
            }
         }
      });
      // <Esc>按键处理
      window.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) {
               isRun = false;
            }
         }
      });

      JLabel label = new JLabel();
      window.setContentPane(label);
      window.setVisible(true);
      // 连接相机
      VideoCapture camera = new VideoCapture(0);
      if (!camera.isOpened()) {
         window.setTitle("无法连接相机");
         isRun = false;
         isEnd = true;
         return;
      }
      
      try {
         // 调整框架尺寸
         camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
         camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
         // 读框架
         Mat frame = new Mat();
         BufferedImage img = null;
         while ( isRun ) {
            if (camera.read(frame)) {
               // 在这里您可以插入用于处理框架的代码
            	//frame = CvAction.Canny(frame);
            	//QrCode.start(frame);
               img = CvUtils.MatToBufferedImage(frame);
               
               QrCode.start(img);
               
               if (img != null) {
                  ImageIcon imageIcon = new ImageIcon(img);
                  label.setIcon(imageIcon);
                  label.repaint();
                  window.pack();
               }
               try {
                  Thread.sleep(10); // 每秒10帧
               } catch (InterruptedException e) {}
            }
            else {
               System.out.println("捕获帧失败");
               break;
            }
         }
      }
      finally {
         camera.release();
         isRun = false;
         isEnd = true;
      }
      window.setTitle("相机已禁用");
   }
}
