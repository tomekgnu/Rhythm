package misc;

import java.io.*;
import java.util.*;
import gnu.io.*;

public class UsbWriter {
    private static Enumeration portList;
    private static CommPortIdentifier portId;
    private static SerialPort serialPort;
    private static OutputStream outputStream;
    
//    public static void main(String[] args){
//        boolean result = init("COM8","Rhythm");
//        result = sendBytes("\n".getBytes());
//        
//    }
    
    public static boolean deInit(){
        if(serialPort != null)
            serialPort.close();
        return true;
    }
    
    public static boolean init(String port,String appName) {
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
	    portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(port)) {
                    try {
                        serialPort = (SerialPort)portId.open(appName, 2000);
                    } catch (PortInUseException e) {                    
                        System.out.println("PortInUseException: " + e.currentOwner);
                        return false;
                    }
                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    try {
                        serialPort.setSerialPortParams(9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    
                }
            }
        }
        
        return true;
    }   

    public static boolean sendBytes(byte[] stream) {
        try {
            outputStream.write(stream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
}
