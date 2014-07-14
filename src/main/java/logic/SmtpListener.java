package logic;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


import logic.propfile.PropFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import logic.parse.Distributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.PropfileDao;


public class SmtpListener {
	//メンバ変数
	private static final Logger log = LoggerFactory.getLogger(SmtpListener.class);
	private static PropFileService propfileInstance = PropFileService.getInstance();
	
	//コンストラクタ
	private SmtpListener() {}
	
	public static void startPkg() {
		propFileController();
	}
	
	//env.propertiesファイルをチェックする
	public static void propFileController() {

		propfileInstance.init();
		if(!propfileInstance.getDomBundles().isEmpty())
			smtpListenerController();
	}
	
	//ソケットを開き、システムをスタート
	public static void smtpListenerController() {
		try {
			ServerSocket sSocket;
			Socket socket;
			int RECEIV_PORT;
			
			ResourceBundle envBundle = propfileInstance.getEnvBundle();
			
			RECEIV_PORT = Integer.parseInt(envBundle.getString("receive_port"));
			sSocket = new ServerSocket(RECEIV_PORT);
			log.info("RMX System begin");
			log.debug("-----START<<PORT_NUM = "+RECEIV_PORT+">>-----");

			while(true) {
				//メールがメールサーバにくると、originalMessageインスタンスを作成
				
				socket = sSocket.accept();
				log.debug("S :Accepted: ("
						+ socket.getInetAddress().getHostName() + ")");

				Thread t = new Thread(new Distributor(socket,propfileInstance));
				t.start();
			}
		}catch (SecurityException e) {
			log.error("# Error: " + e.toString());
			System.exit(-1);
		}catch (Exception e) {
			log.error("# Error: " + e.toString());
			System.exit(-1);
		}
	}
}